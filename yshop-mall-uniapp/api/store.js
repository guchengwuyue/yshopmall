import request from "@/utils/request";

/*
 * 商品分类
 * */
export function getCategory() {
	return request.get("/category", {}, {
		login: false
	});
}

/*
 * 商品详情
 * */
export function getProductDetail(id, data) {
	return request.get("/product/detail/" + id, data, {
		login: true
	});
}

/*
 * 商品分销二维码
 * */
export function getProductCode(id) {
	return request.get("/product/code/" + id, {}, {
		login: true
	});
}

/*
 * 商品列表
 * */
export function getProducts(q) {
	return request.get("/products", q, {
		login: false
	});
}
/*
 * 积分商品列表
 * */
export function getProductsIntegral(q) {
	return request.get("/products/integral", q, {
		login: false
	});
}

/*
 * 购物车数量
 * */
export function getCartNum() {
	return request.get("/cart/count");
}

/*
 * 添加收藏
 * */
export function toCollect(id, category) {
	return request.get("/collect/add/" + id + "/" + category);
}

/*
 * 为你推荐
 * */
export function getHostProducts(page, limit) {
	return request.get(
		"/product/hot", {
		page: page,
		limit: limit
	}, {
		login: false
	}
	);
}

/*
 * 精品、热门、首发列表
 * */
export function getGroomList(type) {
	return request.get("/groom/list/" + type, {}, {
		login: true
	});
}
/*
 * 获取商品海报
 * */
export function getProductPoster(id, data) {
	return request.get("/product/poster/" + id, data, {
		login: true
	});
}

/*
 * 购物车 添加
 * */
export function postCartAdd(data) {
	return request.post("/cart/add", data);
}

/*
 * 购物车列表
 * */
export function getCartList() {
	return request.get("/cart/list");
}

/*
 * 购物车 删除
 * */
export function postCartDel(ids) {
	return request.post("/cart/del", {
		ids
	});
}

/*
 * 购物车 获取数量
 * */
export function getCartCount(data) {
	return request.get("/cart/count", data);
}

/*
 * 购物车 修改商品数量
 * */
export function changeCartNum(id, number) {
	return request.post("/cart/num", {
		id,
		number
	});
}

/**
 * 搜索推荐关键字
 */
export function getSearchKeyword() {
	return request.get("/search/keyword", {}, {
		login: false
	});
}

/**
 * 产品评论列表
 */
export function getReplyList(id, q) {
	return request.get("/reply/list/" + id, q, {
		login: true
	});
}

/**
 * 产品评价数量和好评度
 */
export function getReplyConfig(id) {
	return request.get("/reply/config/" + id, {}, {
		login: true
	});
}

/**
 * 评价页面获取单个产品详情
 */
export function postOrderProduct(unique) {
	return request.post("/order/product", {
		unique
	}, {
		login: true
	});
}

/**
 * 提交评价页面；
 */
export function postOrderComment(data) {
	return request.post("/order/comment", data, {
		login: true
	});
}

export function storeListApi(data) {
	return request.get("store_list", data, {
		login: false
	});
}
