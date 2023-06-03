package co.yixiang.oss.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import co.yixiang.oss.config.OssProperties;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * aws-s3 通用存储操作 支持所有兼容s3协议的云存储: {阿里云OSS，腾讯云COS，七牛云，京东云，minio 等}
 *
 * @date 2023/5/23 6:36 上午
 * @since 1.0
 */
@RequiredArgsConstructor
public class OssTemplate implements InitializingBean {

	private final OssProperties ossProperties;
	private AmazonS3 amazonS3;

	/**
	 * 创建bucket
	 * @param bucketName bucket名称
	 */
	@SneakyThrows
	public void createBucket(String bucketName) {
		if (!amazonS3.doesBucketExistV2(bucketName)) {
			amazonS3.createBucket((bucketName));
		}
	}

	/**
	 * 获取全部bucket
	 * <p>
	 *
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
	 * API Documentation</a>
	 */
	@SneakyThrows
	public List<Bucket> getAllBuckets() {
		return amazonS3.listBuckets();
	}

	/**
	 * @param bucketName bucket名称
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListBuckets">AWS
	 * API Documentation</a>
	 */
	@SneakyThrows
	public Optional<Bucket> getBucket(String bucketName) {
		return amazonS3.listBuckets().stream().filter(b -> b.getName().equals(bucketName)).findFirst();
	}

	/**
	 * @param bucketName bucket名称
	 * @see <a href=
	 * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteBucket">AWS API
	 * Documentation</a>
	 */
	@SneakyThrows
	public void removeBucket(String bucketName) {
		amazonS3.deleteBucket(bucketName);
	}

	/**
	 * 根据文件前置查询文件
	 * @param bucketName bucket名称
	 * @param prefix 前缀
	 * @param recursive 是否递归查询
	 * @return S3ObjectSummary 列表
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/ListObjects">AWS
	 * API Documentation</a>
	 */
	@SneakyThrows
	public List<S3ObjectSummary> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) {
		ObjectListing objectListing = amazonS3.listObjects(bucketName, prefix);
		return new ArrayList<>(objectListing.getObjectSummaries());
	}

	/**
	 * 获取文件外链
	 * @param objectName 文件名称
	 * @return url
	 * @see AmazonS3#generatePresignedUrl(String bucketName, String key, Date expiration)
	 */
	@SneakyThrows
	public String getObjectURL(String objectName) {
		Date date = new Date();
		DateTime dateTime = DateUtil.offsetMinute(date, 15);
		URL url = amazonS3.generatePresignedUrl(ossProperties.getBucketName(), objectName, dateTime.toJdkDate());
		return url.toString();
	}

	/**
	 * 获取文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @return 二进制流
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS
	 * API Documentation</a>
	 */
	@SneakyThrows
	public S3Object getObject(String bucketName, String objectName) {
		return amazonS3.getObject(bucketName, objectName);
	}


	/**
	 * 内网文件转OSS文件
	 *
	 * @param bucketName bucket名称
	 * @param fileName   文件名称
	 * @param url        url
	 */
//	public void intranetToOSSUrl(String fileName,String url){
//		putObject(ossProperties.getBucketName(),fileName,HttpDownloader.downloadBytes(url));
//	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param bytes 文件
	 * @throws Exception
	 */
	public void putObject(String bucketName, String objectName, byte[] bytes) {
		putObject(bucketName, objectName, bytes, bytes.length, "application/octet-stream");
	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param bytes 文件
	 * @param size 大小
	 * @param contextType 类型
	 * @throws Exception
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/PutObject">AWS
	 * API Documentation</a>
	 */
	public PutObjectResult putObject(String bucketName, String objectName, byte[] bytes, long size,
									 String contextType) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contextType);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		// 上传
		return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);

	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream 文件流
	 * @throws Exception
	 */
	public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
		putObject(bucketName, objectName, stream, (long) stream.available(), "application/octet-stream");
	}

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param stream 文件流
	 * @param size 大小
	 * @param contextType 类型
	 * @throws Exception
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/PutObject">AWS
	 * API Documentation</a>
	 */
	public PutObjectResult putObject(String bucketName, String objectName, InputStream stream, long size,
			String contextType) throws Exception {
		com.amazonaws.services.s3.model.S3Object s3Object = new com.amazonaws.services.s3.model.S3Object();
		// String fileName = getFileName(objectName);
		byte[] bytes = IOUtils.toByteArray(stream);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(size);
		objectMetadata.setContentType(contextType);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		// 上传
		return amazonS3.putObject(bucketName, objectName, byteArrayInputStream, objectMetadata);

	}

	/**
	 * 获取文件信息
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @see <a href="http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/GetObject">AWS
	 * API Documentation</a>
	 */
	public S3Object getObjectInfo(String bucketName, String objectName) throws Exception {
		@Cleanup
		S3Object object = amazonS3.getObject(bucketName, objectName);
		return object;
	}

	/**
	 * 删除文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @throws Exception
	 * @see <a href=
	 * "http://docs.aws.amazon.com/goto/WebAPI/s3-2006-03-01/DeleteObject">AWS API
	 * Documentation</a>
	 */
	public void removeObject(String bucketName, String objectName) throws Exception {
		amazonS3.deleteObject(bucketName, objectName);
	}

	@Override
	public void afterPropertiesSet() {
		System.setProperty("com.amazonaws.sdk.disableCertChecking", "true");
		ClientConfiguration clientConfiguration = new ClientConfiguration();//初始化客户端config
		clientConfiguration.setMaxConnections(ossProperties.getMaxConnections());
		clientConfiguration.setProtocol(Protocol.HTTPS);//设置mos 请求协议为http
		clientConfiguration.setSignerOverride("S3SignerType");
		AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
				ossProperties.getEndpoint(), ossProperties.getRegion());//创建AWS凭证
		AWSCredentials awsCredentials = new BasicAWSCredentials(ossProperties.getAccessKey(),
				ossProperties.getSecretKey());
		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
		this.amazonS3 = AmazonS3Client.builder().withEndpointConfiguration(endpointConfiguration)
				.withClientConfiguration(clientConfiguration).withCredentials(awsCredentialsProvider)
				.disableChunkedEncoding().withPathStyleAccessEnabled(ossProperties.getPathStyleAccess()).build();
	}

}
