# Volley-XUtils-OkHttp三种方式实现单张多张图片上传

<div id="article_content" class="article_content">

<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px">OkHttp可以作为Volley底层传输协议，速度更快，传大量图片建议使用。OkHttp<span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre">更多功能请看<strong><a target="_blank" target="_blank" href="http://blog.csdn.net/dickyqie/article/details/52798995">OkHttp的使用</a></strong></span></span></span></p>
<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px"><br>
</span></span></p>
<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px"><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><span style="font-family:tahoma,arial,宋体">xUtils 支持大文件上传，更全面的http请求协议支持(10种谓词)，拥有更加灵活的ORM，更多的事件注解支持且不受混淆影响...</span><br>
</span></span></span></p>
<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px"><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><span style="font-family:tahoma,arial,宋体"><span style="font-family:tahoma,arial,宋体"><span style="font-family:tahoma,arial,宋体; white-space:pre">xUtils</span>一共有4大功能：注解模块，网络模块，图片加载模块，数据库模块。相关请看<strong><a target="_blank" target="_blank" href="http://blog.csdn.net/dickyqie/article/details/52573888"><span style="font-family:Microsoft YaHei">xUtils框架的使用</span></a></strong>博客</span><br>
</span></span></span></span></p>
<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px"><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><span style="font-family:tahoma,arial,宋体; font-size:14px"><br>
</span></span></span></span></p>
<p><span style="font-family:&quot;microsoft yahei&quot;"><span style="font-size:14px"><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px">使用Volley上传，猪哟<span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px; white-space:pre">是继承volley的Request类</span>，</span></span></span><span style="font-size:14px; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px">然后通过使用httpmim的</span><span style="font-size:14px; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px">MultipartEntity类对文件参数进行封装。</span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><span style="font-size:14px">Volley更多功能请看</span><strong><span style="font-size:14px"><a target="_blank" target="_blank" href="http://blog.csdn.net/dickyqie/article/details/52562815"><span style="font-family:Microsoft YaHei">Volley框架</span>使用</a></span></strong></span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><strong><span style="font-size:14px"><span style="color:#cc0000"><br>
</span></span></strong></span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><strong><span style="color:rgb(204,0,0)"><span style="font-size:24px">所需的jar包：</span></span></strong></span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><strong><span style="color:rgb(204,0,0)"><span style="font-size:24px"><br>
</span></span></strong></span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; line-height:15.4px"><strong><span style="font-size:18px"><a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662207">httpclient-4.3.5.jar</a>，<a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662215">android-async-http-1.4.4.jar</a></span></strong></span></p>
<p>&nbsp;<a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662229" style="font-size:18px; font-weight:bold; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre">httpcore-4.3.2.jar</a>，
 &nbsp; &nbsp; &nbsp; &nbsp;<a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662210" style="font-size:18px; font-weight:bold; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre">httpmime-4.3.5</a></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace"><span style="white-space:pre"><strong><span style="font-size:18px"><a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9652057">okhttp.jar</a>，</span></strong></span></span><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; white-space:pre; font-size:18px"><strong><a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9652349">okio.jar</a>，</strong></span><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace"><span style="white-space:pre"><strong><span style="font-size:18px"><a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662220">volley.jar</a>，</span></strong></span></span><a target="_blank" target="_blank" href="http://download.csdn.net/detail/dickyqie/9662223" style="font-weight:bold; white-space:pre; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace"><span style="font-size:18px">xUtils-2.6.2.jar</span></a></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px"><span style="white-space:pre"><strong><br>
</strong></span></span></p>
<p><span style="white-space:pre; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px"><strong>OkHttp：</strong></span></p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px; white-space:pre"><br>
</span></p>
<p>&nbsp; &nbsp;<strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span></strong> Handler <em><u><span style="color:#0000C0">handler</span></u></em> =<strong><span style="color:#7F0055">new</span></strong>Handler(Looper.<em>getMainLooper</em>());</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final</span></strong> MediaType<em><span style="color:#0000C0">MEDIA_TYPE_PNG</span></em> = MediaType.<em>parse</em>(<span style="color:#2A00FF">&quot;image/*&quot;</span>);</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">final </span></strong>OkHttpClient <em>
<span style="color:#0000C0">client</span></em> = <strong><span style="color:#7F0055">new&nbsp;</span></strong>OkHttpClient.Builder().addInterceptor(<strong><span style="color:#7F0055">new&nbsp;</span></strong>Interceptor() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span></strong> Responseintercept(Chain chain)<strong><span style="color:#7F0055">throws</span></strong> IOException {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; okhttp3.Request request =chain.request().newBuilder()</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .build();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return&nbsp;</span></strong>chain.proceed(request);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }).readTimeout(15, TimeUnit.<em><span style="color:#0000C0">SECONDS</span></em>)<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">设置读取超时时间</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .writeTimeout(15, TimeUnit.<em><span style="color:#0000C0">SECONDS</span></em>)<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">设置写的超时时间</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .connectTimeout(15, TimeUnit.<em><span style="color:#0000C0">SECONDS</span></em>)<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">设置连接超时时间</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .build();</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">上传图片公有方法</span></p>
<p>&nbsp;&nbsp; <strong><span style="color:#7F0055">private</span> <span style="color:#7F0055">
final</span> <span style="color:#7F0055">static</span> <span style="color:#7F0055">
void </span></strong>uploadImgAndParameter(Map&lt;String, Object&gt; map,String url) {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:rgb(63,127,95)">// mImgUrls</span><span style="color:#3F7F5F">为存放图片的</span><u><span style="color:#3F7F5F">url</span></u><span style="color:#3F7F5F">集合</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MultipartBody.Builder builder = <strong><span style="color:#7F0055">new </span>
</strong>MultipartBody.Builder()</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .setType(MultipartBody.<em><span style="color:#0000C0">FORM</span></em>);</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<strong><span style="color:#7F0055">null</span></strong> != map) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">for</span></strong>(Map.Entry&lt;String, Object&gt; entry : map.entrySet()) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong>(entry.getValue() !=<strong><span style="color:#7F0055">null</span></strong>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong>(entry.getValue()<strong><span style="color:#7F0055">instanceof</span></strong> File) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; File f = (File)entry.getValue();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; builder.addFormDataPart(entry.getKey(),f.getName(),</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RequestBody.<em>create</em>(<em><span style="color:#0000C0">MEDIA_TYPE_PNG</span></em>, f));</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; } <strong><span style="color:#7F0055">else</span></strong> {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; builder.addFormDataPart(entry.getKey(),entry</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .getValue().toString());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">创建</span><span style="color:#3F7F5F">RequestBody</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RequestBody body = builder.build();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">final </span></strong>okhttp3.Request request =<strong><span style="color:#7F0055">new&nbsp;</span></strong>okhttp3.Request.Builder().url(url)<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">地址</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .post(body)<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">添加请求体</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; .build();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <em><span style="color:#0000C0">client</span></em>.newCall(request).enqueue(<strong><span style="color:#7F0055">new</span></strong>okhttp3.Callback() {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void&nbsp;</span></strong>onResponse(Call call, <strong><span style="color:#7F0055">final</span></strong> Response response)&nbsp;<strong><span style="color:#7F0055">throws&nbsp;</span></strong>IOException {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">final</span></strong> Stringdata = response.body().string();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em>, <span style="color:#2A00FF">
&quot;</span><span style="color:#2A00FF">上传照片成功</span><span style="color:#2A00FF">--&gt;&quot;</span> &#43; data);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; call.cancel();<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">上传成功取消请求释放内存</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void </span></strong>onFailure(Call call, <strong><span style="color:#7F0055">final</span></strong> IOException e) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em>, <span style="color:#2A00FF">
&quot;</span><span style="color:#2A00FF">上传失败</span><span style="color:#2A00FF">--&gt;&quot;</span> &#43; e.getMessage());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; call.cancel();<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">上传失败取消请求释放内存</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; });</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; }</p>
<p><br>
</p>
<p><span style="font-family:Consolas,Bitstream Vera Sans Mono,Courier New,Courier,monospace; font-size:14px"><span style="white-space:pre"><strong>XUtils：</strong></span></span><span style="white-space:pre; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px"></span></p>
<p><span style="font-family:Consolas,Bitstream Vera Sans Mono,Courier New,Courier,monospace; font-size:14px"><span style="white-space:pre"></span></span></p>
<p>HttpUtils http = <strong><span style="color:#7F0055">new </span></strong>HttpUtils();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RequestParams params = <strong><span style="color:#7F0055">new </span></strong>RequestParams();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;c&quot;</span>, <span style="color:#2A00FF">
&quot;comment&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;a&quot;</span>, <span style="color:#2A00FF">
&quot;add&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;uid&quot;</span>, <span style="color:#2A00FF">
&quot;1000191&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;dataid&quot;</span>, <span style="color:#2A00FF">
&quot;1114&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;message&quot;</span>, <span style="color:#2A00FF">
&quot;</span><span style="color:#2A00FF">你好</span><span style="color:#2A00FF">&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;datatype&quot;</span>, <span style="color:#2A00FF">
&quot;goodsid&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">for</span></strong> (<strong><span style="color:#7F0055">int</span></strong> i = 0; i&lt; 2; i&#43;&#43;) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.addBodyParameter(<span style="color:#2A00FF">&quot;filedata&quot;</span> &#43; i,<span style="color:#0000C0">file</span>[i]);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; http.send(HttpRequest.HttpMethod.<em><span style="color:#0000C0">POST</span></em>,<em><span style="color:#0000C0">url</span></em>, params,<strong><span style="color:#7F0055">new&nbsp;</span></strong>RequestCallBack&lt;String&gt;() {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void </span></strong>onFailure(HttpException arg0, String arg1) {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void </span></strong>onSuccess(ResponseInfo&lt;String&gt; arg0) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em>, arg0.<span style="color:#0000C0">result</span>.toString());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; });</p>
<p></p>
<p><br>
</p>
<p></p>
<p><strong><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace"><span style="white-space:pre">Volley：</span></span><span style="white-space:pre; font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace"></span></strong></p>
<p></p>
<p>Map&lt;String,File&gt; files = <strong><span style="color:#7F0055">new</span></strong> HashMap&lt;String, File&gt;();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; files.put(<span style="color:#2A00FF">&quot;filedata1&quot;</span>, <span style="color:#0000C0">
file</span>[0]);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; files.put(<span style="color:#2A00FF">&quot;filedata2&quot;</span>, <span style="color:#0000C0">
file</span>[1]);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; files.put(<span style="color:#2A00FF">&quot;filedata3&quot;</span>, <span style="color:#0000C0">
file</span>[2]);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Map&lt;String, String&gt; params = <strong><span style="color:#7F0055">new </span>
</strong>HashMap&lt;String, String&gt;();</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;c&quot;</span>, <span style="color:#2A00FF">
&quot;comment&quot;</span>);</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;a&quot;</span>, <span style="color:#2A00FF">
&quot;add&quot;</span>);<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">参数</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;uid&quot;</span>, <span style="color:#2A00FF">
&quot;1000191&quot;</span>);<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">参数</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;dataid&quot;</span>, <span style="color:#2A00FF">
&quot;1111&quot;</span>);<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">参数</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;message&quot;</span>, <span style="color:#2A00FF">
&quot;</span><span style="color:#2A00FF">你好</span><span style="color:#2A00FF">&quot;</span>);<span style="color:#3F7F5F">//</span><span style="color:#3F7F5F">参数</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; params.put(<span style="color:#2A00FF">&quot;datatype&quot;</span>, <span style="color:#2A00FF">
&quot;goodsid&quot;</span>);<span style="color:#3F7F5F">// </span><span style="color:#3F7F5F">参数</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <em>addPutUploadFileRequest</em>(files,params, <span style="color:#0000C0">
mResonseListenerString</span>,<span style="color:rgb(0,0,192)">mErrorListener</span>);</p>
<p><br>
</p>
<p></p>
<p><strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
static</span> <span style="color:#7F0055">void</span></strong> <span style="background:silver">
addPutUploadFileRequest</span>(<strong><span style="color:#7F0055">final </span></strong>Map&lt;String, File&gt; files,</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">final</span></strong>Map&lt;String, String&gt; params,</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">final</span></strong>Listener&lt;String&gt; responseListener,</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">final</span></strong>ErrorListener errorListener) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (<strong><span style="color:#7F0055">null</span></strong> ==<em><span style="color:#0000C0">url</span></em> ||<strong><span style="color:#7F0055">null</span></strong> ==responseListener) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong>;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MultiPartStringRequest multiPartRequest = <strong><span style="color:#7F0055">new</span></strong>MultiPartStringRequest(</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Request.Method.<em><span style="color:#0000C0">POST</span></em>, <em>
<span style="color:#0000C0">url</span></em>,responseListener, errorListener) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span></strong>Map&lt;String, File&gt; getFileUploads() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> files;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span></strong>Map&lt;String, String&gt; getStringUploads() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">return</span></strong> params;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; };</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <em><span style="color:#0000C0">mSingleQueue</span></em>.add(multiPartRequest);</p>
<p>&nbsp;&nbsp; }</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; Listener&lt;JSONObject&gt; <span style="color:#0000C0">mResonseListener</span> = <strong>
<span style="color:#7F0055">new </span></strong>Listener&lt;JSONObject&gt;() {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void</span></strong>onResponse(JSONObject response) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em> &#43; <span style="color:#2A00FF">
&quot;1&quot;</span>,response.toString());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp; };</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; Listener&lt;String&gt; <span style="color:#0000C0">mResonseListenerString</span> =<strong><span style="color:#7F0055">new</span></strong>Listener&lt;String&gt;() {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void</span></strong>onResponse(String response) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em> &#43; <span style="color:#2A00FF">
&quot;2&quot;</span>,response.toString());</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp; };</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp; ErrorListener <span style="color:#0000C0">mErrorListener</span> = <strong><span style="color:#7F0055">new</span></strong>ErrorListener() {</p>
<p>&nbsp;</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#646464">@Override</span></p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">public</span> <span style="color:#7F0055">
void</span></strong>onErrorResponse(VolleyError error) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (error !=<strong><span style="color:#7F0055">null</span></strong>) {</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><span style="color:#7F0055">if</span></strong> (error.<span style="color:#0000C0">networkResponse</span> !=<strong><span style="color:#7F0055">null</span></strong>)</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Log.<em>i</em>(<em><span style="color:#0000C0">TAG</span></em> &#43;<span style="color:#2A00FF">&quot;3&quot;</span>,<span style="color:#2A00FF">&quot;error &quot;</span><strong><span style="color:#7F0055">new</span></strong>String(error.<span style="color:rgb(0,0,192)">networkResponse</span>.<span style="color:rgb(0,0,192)">data</span>));</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }</p>
<p>&nbsp;&nbsp; };</p>
<p><span style="font-family:Consolas,&quot;Bitstream Vera Sans Mono&quot;,&quot;Courier New&quot;,Courier,monospace; font-size:14px"><br>
</span></p>
<h3 style="margin:24px 0px 16px; padding:0px; font-family:Arial; line-height:1.25; color:rgb(51,51,51)">
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-size:14px">
<span style="font-size:18px"></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px">
<span style="color:rgb(51,51,51); font-family:&quot;microsoft yahei&quot;; font-size:24px"><strong>不要忘记</strong></span><span style="color:rgb(51,51,51); font-family:&quot;microsoft yahei&quot;; font-size:24px"><strong>在</strong></span><span style="font-family:&quot;microsoft yahei&quot;; font-size:24px; color:rgb(51,102,255)"><strong>AndroidManifest.xml</strong></span><span style="color:rgb(51,51,51); font-family:&quot;microsoft yahei&quot;; font-size:24px"><strong>加权限哦！</strong></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px">
<span style="color:rgb(51,51,51); font-family:&quot;microsoft yahei&quot;; font-size:24px"><strong><br>
</strong></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px">
<span style="color:rgb(51,51,51); font-family:&quot;microsoft yahei&quot;; font-size:24px"><strong></strong></span></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px">
<strong><span style="font-size:18px">由于代码太多，完整代码未给出，源码直接下载即可</span></strong></p>
<p style="margin-top:0px; margin-bottom:0px; padding-top:0px; padding-bottom:0px; font-family:Arial; font-size:14px">
<p style="font-weight:bold"></p>
</div>
