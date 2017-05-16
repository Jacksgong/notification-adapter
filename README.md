# Notification适配器

> Notification风格适配，提取默认Notification的颜色

> 编写这个的目的是目前还有好多应用自定义Notification的布局是hardcode的，导致在不同的系统主题中显得格格不入(如Wifi万能钥匙、支付宝等)，这个问题其实在2013年年底我在微信就解决了该问题了，希望能够给大家带来思路。

## 辣眼睛案例

> 业界其实有很多很多Notification自定义布局并且hardcoard的案例，好不和谐....

![](https://raw.githubusercontent.com/Jacksgong/notification-adapter/master/art/alipay-demo.jpeg)

![](https://raw.githubusercontent.com/Jacksgong/notification-adapter/master/art/wifi-master-key-demo.jpeg)

## 解决方案

基本的解决思路是创建一个系统默认的Notification，然后获取得背景颜色、标题颜色等。

## 使用

Gradle引用

```

```

通过方法或者颜色

```
```


当然作为Notification，其实随着迭代演进已经有了很多很多很给力的Feature需要去适配，也欢迎大家到[Notification最佳实践](https://blog.dreamtobe.cn/2016/01/09/notification_best_practise/)拍砖，共同维护更好的Android环境。
