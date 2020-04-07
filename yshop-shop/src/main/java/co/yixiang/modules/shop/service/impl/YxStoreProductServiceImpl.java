package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import co.yixiang.constant.ShopConstants;
import co.yixiang.exception.BadRequestException;
import co.yixiang.modules.shop.domain.YxStoreProduct;
import co.yixiang.modules.shop.domain.YxStoreProductAttr;
import co.yixiang.modules.shop.domain.YxStoreProductAttrResult;
import co.yixiang.modules.shop.domain.YxStoreProductAttrValue;
import co.yixiang.modules.shop.repository.*;
import co.yixiang.modules.shop.service.YxStoreProductService;
import co.yixiang.modules.shop.service.dto.*;
import co.yixiang.modules.shop.service.mapper.YxStoreProductMapper;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author hupeng
* @date 2019-10-04
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxStoreProductServiceImpl implements YxStoreProductService {

    private final YxStoreProductRepository yxStoreProductRepository;
    private final YxStoreProductAttrRepository yxStoreProductAttrRepository;
    private final YxStoreProductAttrValueRepository yxStoreProductAttrValueRepository;
    private final YxStoreProductAttrResultRepository yxStoreProductAttrResultRepository;

    private final YxStoreProductMapper yxStoreProductMapper;

    public YxStoreProductServiceImpl(YxStoreProductRepository yxStoreProductRepository,
                                     YxStoreProductAttrRepository yxStoreProductAttrRepository, YxStoreProductAttrValueRepository yxStoreProductAttrValueRepository,
                                     YxStoreProductAttrResultRepository yxStoreProductAttrResultRepository, YxStoreProductMapper yxStoreProductMapper) {
        this.yxStoreProductRepository = yxStoreProductRepository;
        this.yxStoreProductAttrRepository = yxStoreProductAttrRepository;
        this.yxStoreProductAttrValueRepository = yxStoreProductAttrValueRepository;
        this.yxStoreProductAttrResultRepository = yxStoreProductAttrResultRepository;
        this.yxStoreProductMapper = yxStoreProductMapper;
    }

    @Override
    public Map<String,Object> queryAll(YxStoreProductQueryCriteria criteria, Pageable pageable){
        //criteria.setIsDel(0);
        Page<YxStoreProduct> page = yxStoreProductRepository
                .findAll((root, criteriaQuery, criteriaBuilder)
                                -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)
                        ,pageable);
        List<YxStoreProductDTO> storeProductDTOS = new ArrayList<>();
        for (YxStoreProduct product : page.getContent()) {

            YxStoreProductDTO yxStoreProductDTO = yxStoreProductMapper.toDto(product);

            //规格属性库存
            Integer newStock = yxStoreProductAttrValueRepository.sumStock(product.getId());
            if(newStock != null) yxStoreProductDTO.setStock(newStock);

            storeProductDTOS.add(yxStoreProductDTO);
        }
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",storeProductDTOS);
        map.put("totalElements",page.getTotalElements());
        return map;
    }
    @Override
    public List<YxStoreProductDTO> queryAll(YxStoreProductQueryCriteria criteria){
        return yxStoreProductMapper.toDto(yxStoreProductRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxStoreProductDTO findById(Integer id) {
        Optional<YxStoreProduct> yxStoreProduct = yxStoreProductRepository.findById(id);
        ValidationUtil.isNull(yxStoreProduct,"YxStoreProduct","id",id);
        return yxStoreProductMapper.toDto(yxStoreProduct.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxStoreProductDTO create(YxStoreProduct resources) {
        return yxStoreProductMapper.toDto(yxStoreProductRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxStoreProduct resources) {
        Optional<YxStoreProduct> optionalYxStoreProduct = yxStoreProductRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalYxStoreProduct,"YxStoreProduct","id",resources.getId());
        YxStoreProduct yxStoreProduct = optionalYxStoreProduct.get();
        yxStoreProduct.copy(resources);
        yxStoreProductRepository.save(yxStoreProduct);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        yxStoreProductRepository.updateDel(1,id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recovery(Integer id) {
        yxStoreProductRepository.updateDel(0,id);
        yxStoreProductRepository.updateOnsale(0,id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onSale(Integer id, Integer status) {
        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }
        yxStoreProductRepository.updateOnsale(status,id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ProductFormatDTO> isFormatAttr(Integer id, String jsonStr) {
        if(ObjectUtil.isNull(id)) throw new BadRequestException("产品不存在");

        YxStoreProductDTO yxStoreProductDTO = findById(id);

        DetailDTO detailDTO = attrFormat(jsonStr);

        //System.out.println("list:"+detailDTO.getRes());
        List<ProductFormatDTO> newList = new ArrayList<>();
        for (Map<String, Map<String,String>> map : detailDTO.getRes()) {
            ProductFormatDTO productFormatDTO = new ProductFormatDTO();

            productFormatDTO.setDetail(map.get("detail"));
            productFormatDTO.setCost(yxStoreProductDTO.getCost().doubleValue());
            productFormatDTO.setPrice(yxStoreProductDTO.getPrice().doubleValue());
            productFormatDTO.setSales(yxStoreProductDTO.getSales());
            productFormatDTO.setPic(yxStoreProductDTO.getImage());
            productFormatDTO.setCheck(false);
            newList.add(productFormatDTO);

        }


        return newList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductAttr(Integer id, String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        //System.out.println(jsonObject);
        List<FromatDetailDTO> attrList = JSON.parseArray(
                jsonObject.get("items").toString(),
                FromatDetailDTO.class);
        List<ProductFormatDTO> valueList = JSON.parseArray(
                jsonObject.get("attrs").toString(),
                ProductFormatDTO.class);


        List<YxStoreProductAttr> attrGroup = new ArrayList<>();
        for (FromatDetailDTO fromatDetailDTO : attrList) {
            YxStoreProductAttr  yxStoreProductAttr = new YxStoreProductAttr();
            yxStoreProductAttr.setProductId(id);
            yxStoreProductAttr.setAttrName(fromatDetailDTO.getValue());
            yxStoreProductAttr.setAttrValues(StrUtil.
                    join(",",fromatDetailDTO.getDetail()));
            attrGroup.add(yxStoreProductAttr);
        }


        List<YxStoreProductAttrValue> valueGroup = new ArrayList<>();
        for (ProductFormatDTO productFormatDTO : valueList) {
            YxStoreProductAttrValue yxStoreProductAttrValue = new YxStoreProductAttrValue();
            yxStoreProductAttrValue.setProductId(id);
            //productFormatDTO.getDetail().values().stream().collect(Collectors.toList());
            List<String> stringList = productFormatDTO.getDetail().values()
                    .stream().collect(Collectors.toList());
            Collections.sort(stringList);
            yxStoreProductAttrValue.setSuk(StrUtil.
                    join(",",stringList));
            yxStoreProductAttrValue.setPrice(BigDecimal.valueOf(productFormatDTO.getPrice()));
            yxStoreProductAttrValue.setCost(BigDecimal.valueOf(productFormatDTO.getCost()));
            yxStoreProductAttrValue.setStock(productFormatDTO.getSales());
            yxStoreProductAttrValue.setUnique(IdUtil.simpleUUID());
            yxStoreProductAttrValue.setImage(productFormatDTO.getPic());

            valueGroup.add(yxStoreProductAttrValue);
        }

        if(attrGroup.isEmpty() || valueGroup.isEmpty()){
            throw new BadRequestException("请设置至少一个属性!");
        }

        //插入之前清空
        clearProductAttr(id,false);


        //保存属性
        yxStoreProductAttrRepository.saveAll(attrGroup);

        //保存值
        yxStoreProductAttrValueRepository.saveAll(valueGroup);

        Map<String,Object> map = new LinkedHashMap<>();
        map.put("attr",jsonObject.get("items"));
        map.put("value",jsonObject.get("attrs"));

        //保存结果
        setResult(map,id);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setResult(Map<String, Object> map,Integer id) {
        YxStoreProductAttrResult yxStoreProductAttrResult = new YxStoreProductAttrResult();
        yxStoreProductAttrResult.setProductId(id);
        yxStoreProductAttrResult.setResult(JSON.toJSONString(map));
        yxStoreProductAttrResult.setChangeTime(OrderUtil.getSecondTimestampTwo());

        yxStoreProductAttrResultRepository.deleteByProductId(id);

        yxStoreProductAttrResultRepository.save(yxStoreProductAttrResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void clearProductAttr(Integer id,boolean isActice) {
        if(ObjectUtil.isNull(id)) throw new BadRequestException("产品不存在");

        yxStoreProductAttrRepository.deleteByProductId(id);
        yxStoreProductAttrValueRepository.deleteByProductId(id);

        if(isActice){
            yxStoreProductAttrResultRepository.deleteByProductId(id);
        }
    }

    @Override
    public String getStoreProductAttrResult(Integer id) {
        YxStoreProductAttrResult yxStoreProductAttrResult = yxStoreProductAttrResultRepository
                .findByProductId(id);
        if(ObjectUtil.isNull(yxStoreProductAttrResult)) return "";
        return  yxStoreProductAttrResult.getResult();
    }

    /**
     * 组合规则属性算法
     * @param jsonStr
     * @return
     */
    public DetailDTO attrFormat(String jsonStr){
       // List<Object> returnList = new ArrayList<>();

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<FromatDetailDTO> fromatDetailDTOList = JSON.parseArray(jsonObject.get("items").toString(),
                FromatDetailDTO.class);
        List<String> data = new ArrayList<>();
        //List<Map<String,List<Map<String,String>>>> res =new ArrayList<>();
        List<Map<String,Map<String,String>>> res =new ArrayList<>();

        if(fromatDetailDTOList.size() > 1){
            for (int i=0; i < fromatDetailDTOList.size() - 1;i++){
                if(i == 0) data = fromatDetailDTOList.get(i).getDetail();
                List<String> tmp = new LinkedList<>();
                for (String v : data) {
                    for (String g : fromatDetailDTOList.get(i+1).getDetail()) {
                        String rep2 = "";
                        if(i == 0){
                            rep2 = fromatDetailDTOList.get(i).getValue() + "_" + v + "-"
                                    + fromatDetailDTOList.get(i+1).getValue() + "_" + g;
                        }else{
                            rep2 = v + "-"
                                    + fromatDetailDTOList.get(i+1).getValue() + "_" + g;
                        }

                        tmp.add(rep2);

                        if(i == fromatDetailDTOList.size() - 2){
                           // Map<String,List<Map<String,String>>> rep4 = new LinkedHashMap<>();
                            Map<String,Map<String,String>> rep4 = new LinkedHashMap<>();
                            //List<Map<String,String>> listMap = new ArrayList<>();
                            //Map<String,String> map1 = new LinkedHashMap<>();
                            Map<String,String> reptemp = new LinkedHashMap<>();
                            for (String h : Arrays.asList(rep2.split("-"))) {
                                List<String> rep3 = Arrays.asList(h.split("_"));

                                if(rep3.size() > 1){
                                    reptemp.put(rep3.get(0),rep3.get(1));
                                }else{
                                    reptemp.put(rep3.get(0),"");
                                }
                                //listMap.add(reptemp);

                            }
                            rep4.put("detail",reptemp);

                            //rep4.put("detail",listMap);

                            res.add(rep4);
                        }
                    }

                }

                //System.out.println("tmp:"+tmp);
                if(!tmp.isEmpty()){
                    data = tmp;
                }
            }
        }else{
            List<String> dataArr = new ArrayList<>();

            for (FromatDetailDTO fromatDetailDTO : fromatDetailDTOList) {

                for (String str : fromatDetailDTO.getDetail()) {
                    Map<String,Map<String,String>> map2 = new LinkedHashMap<>();
                    //List<Map<String,String>> list1 = new ArrayList<>();
                    dataArr.add(fromatDetailDTO.getValue()+"_"+str);
                    Map<String,String> map1 = new LinkedHashMap<>();
                    map1.put(fromatDetailDTO.getValue(),str);
                    //list1.add(map1);
                    map2.put("detail",map1);
                    res.add(map2);
                }
            }

            String s = StrUtil.join("-",dataArr);
            data.add(s);
        }


        DetailDTO detailDTO = new DetailDTO();
        detailDTO.setData(data);
        detailDTO.setRes(res);


        return detailDTO;
    }

}