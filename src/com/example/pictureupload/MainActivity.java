package com.example.pictureupload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

import util.MultiPartStack;
import util.MultiPartStringRequest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/*****
 * 
 * 
 * 图片上传（单张 ， 多张）
 * 
 * @author zq
 * 
 */

public class MainActivity extends Activity implements OnClickListener {
	private static RequestQueue mSingleQueue;
	private File[] file;
	private static String url = "";
	private static String TAG = "test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		mSingleQueue = Volley.newRequestQueue(this, new MultiPartStack());
		showFile();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			Map<String, File> files = new HashMap<String, File>();
			files.put("filedata1", file[0]);
			files.put("filedata2", file[1]);
			files.put("filedata3", file[2]);
			Map<String, String> params = new HashMap<String, String>();
			params.put("c", "comment");
			params.put("a", "add");// 参数
			params.put("uid", "1000191");// 参数
			params.put("dataid", "1111");// 参数
			params.put("message", "你好");// 参数
			params.put("datatype", "goodsid");// 参数
			addPutUploadFileRequest(files, params, mResonseListenerString,
					mErrorListener);
			break;
		case R.id.button2:
			showXUtils();
			break;
		case R.id.button3:
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("shopperId", "12312");
			map.put("c", "comment");
			map.put("a", "add");
			map.put("uid", "1000191");
			map.put("dataid", "114");
			map.put("message", "你好");
			map.put("datatype", "goodsid");
			for (int i = 0; i < file.length; i++) {
				map.put("filedata" + i, file[i]);
			}
			uploadImgAndParameter(map, url);
			break;

		default:
			break;
		}
	}

	/****
	 * 
	 * Volley上传
	 * 
	 * @param files
	 * @param params
	 * @param responseListener
	 * @param errorListener
	 */

	public static void addPutUploadFileRequest(final Map<String, File> files,
			final Map<String, String> params,
			final Listener<String> responseListener,
			final ErrorListener errorListener) {
		if (null == url || null == responseListener) {
			return;
		}

		MultiPartStringRequest multiPartRequest = new MultiPartStringRequest(
				Request.Method.POST, url, responseListener, errorListener) {

			@Override
			public Map<String, File> getFileUploads() {
				return files;
			}

			@Override
			public Map<String, String> getStringUploads() {
				return params;
			}

		};

		mSingleQueue.add(multiPartRequest);
	}

	Listener<JSONObject> mResonseListener = new Listener<JSONObject>() {

		@Override
		public void onResponse(JSONObject response) {
			Log.i(TAG + "1", response.toString());
		}
	};

	Listener<String> mResonseListenerString = new Listener<String>() {

		@Override
		public void onResponse(String response) {
			Log.i(TAG + "2", response.toString());
		}
	};

	ErrorListener mErrorListener = new ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			if (error != null) {
				if (error.networkResponse != null)
					Log.i(TAG + "3", " error "
							+ new String(error.networkResponse.data));
			}
		}
	};

	/****
	 * xUtils上传
	 * 
	 */
	private void showXUtils() {
		// TODO Auto-generated method stub
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("c", "comment");
		params.addBodyParameter("a", "add");
		params.addBodyParameter("uid", "1000191");
		params.addBodyParameter("dataid", "1114");
		params.addBodyParameter("message", "你好");
		params.addBodyParameter("datatype", "goodsid");
		for (int i = 0; i < 2; i++) {
			params.addBodyParameter("filedata" + i, file[i]);
		}
		http.send(HttpRequest.HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Log.i(TAG, arg0.result.toString());
					}

				});

	}

	/*****
	 * 
	 * okHttp上传
	 * 
	 * 
	 * 
	 */

	private static final Handler handler = new Handler(Looper.getMainLooper());

	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

	private static final OkHttpClient client = new OkHttpClient.Builder()
			.addInterceptor(new Interceptor() {
				@Override
				public Response intercept(Chain chain) throws IOException {
					okhttp3.Request request = chain.request().newBuilder()
							.build();
					return chain.proceed(request);
				}
			}).readTimeout(15, TimeUnit.SECONDS)// 设置读取超时时间
			.writeTimeout(15, TimeUnit.SECONDS)// 设置写的超时时间
			.connectTimeout(15, TimeUnit.SECONDS)// 设置连接超时时间
			.build();

	// 上传图片公有方法
	private final static void uploadImgAndParameter(Map<String, Object> map,
			String url) {

		// mImgUrls为存放图片的url集合
		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);

		if (null != map) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					if (entry.getValue() instanceof File) {
						File f = (File) entry.getValue();
						builder.addFormDataPart(entry.getKey(), f.getName(),
								RequestBody.create(MEDIA_TYPE_PNG, f));
					} else {
						builder.addFormDataPart(entry.getKey(), entry
								.getValue().toString());
					}
				}

			}
		}
		// 创建RequestBody
		RequestBody body = builder.build();
		final okhttp3.Request request = new okhttp3.Request.Builder().url(url)// 地址
				.post(body)// 添加请求体
				.build();
		client.newCall(request).enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call call, final Response response)
					throws IOException {
				final String data = response.body().string();
				Log.i(TAG, "上传照片成功-->" + data);
				call.cancel();// 上传成功取消请求释放内存
			}

			@Override
			public void onFailure(Call call, final IOException e) {
				Log.i(TAG, "上传失败-->" + e.getMessage());
				call.cancel();// 上传失败取消请求释放内存
			}

		});

	}

	private void showFile() {
		// TODO Auto-generated method stub
		String path1 = "/storage/emulated/legacy/Android/data/cn.zhangwoo.zhangwo.activity/cache/images/mnd.jpg";
		File file1 = new File(path1);
		String path2 = "/storage/emulated/legacy/Tencent/MobileQQ/qbiz/html5/165/s2.url.cn/"
				+ "qqweb/m/relativegroup/css/image/inland_group0.jpg";
		File file2 = new File(path2);
		String path3 = "/storage/emulated/legacy/Tencent/MobileQQ/qbiz/html5/165/s2.url.cn/"
				+ "qqweb/m/relativegroup/css/image/inland_group1.jpg";
		File file3 = new File(path3);
		file = new File[] { file1, file2, file3 };

	}

}
