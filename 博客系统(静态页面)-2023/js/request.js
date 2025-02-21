import axios from "https://cdn.bootcdn.net/ajax/libs/axios/1.5.0/esm/axios.min.js";
//对axios进行二次封装,将刚才下载好的axios导入进来
//2. 利用axios对象的方法create,去创建一个axios实例
//requests就是axios,只不过稍微配置一下
//requests是个变量，可以是其他

const requests = axios.create({
  //基础路径
  withCredentials: true, // 跨域请求时发送Cookie
  baseURL: "", // 所有请求的公共地址部分
  timeout: 3000, // 请求超时时间,这里的意思是当请求时间超过5秒还未取得结果时,提示用户请求超时
});

// 请求拦截处理 请求拦截 在请求拦截中可以补充请求相关的配置
// interceptors axios的拦截器对象
requests.interceptors.request.use(
  (config) => {
    // config 请求的所有信息
    // console.log(config);
    // 响应成功的返回
    return config; // 将配置完成的config对象返回出来 如果不返回 请求将不会进行
  },
  (err) => {
    // 请求发生错误时的相关处理 抛出错误
    return Promise.reject(err);
  }
);

//响应拦截处理  响应拦截器：包含两个函数（一个是成功返回的函数，一个是失败的返回的函数）
requests.interceptors.response.use(
  (res) => {
    // 我们一般在这里处理，请求成功后的错误状态码 例如状态码是500，404，403
    // res 是所有相应的信息
    console.log(res);
    return res.data;
  },
  (err) => {
    // 服务器响应发生错误时的处理
    return Promise.reject(err);
  }
);

//最后一步 暴露出去实例导出
export default requests;
