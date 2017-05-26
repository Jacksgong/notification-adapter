# Notification适配器

Notification风格适配，提取默认Notification的颜色

[ ![Download](https://api.bintray.com/packages/jacksgong/maven/notification-adapter/images/download.svg) ](https://bintray.com/jacksgong/maven/notification-adapter/_latestVersion)
![](https://img.shields.io/badge/Android-NotificationAdapter-green.svg)

> 编写这个的目的是目前还有好多应用自定义Notification的布局是hardcode的，导致在不同的系统主题中显得格格不入(如Wifi万能钥匙等)，这个问题其实在2013年年底在微信就处理过，希望能够给大家带来思路。

## 辣眼睛案例

> 业界其实有很多很多Notification自定义布局并且hardcoard的案例，好不和谐....

![](https://github.com/Jacksgong/notification-adapter/raw/master/arts/alipay-demo.jpeg)

![](https://github.com/Jacksgong/notification-adapter/raw/master/arts/wifi-master-key-demo.jpeg)

## 解决方案

因此就是使用该工具获得Title与Text的颜色，然后保证背景是透明的，就可以解决该问题。

> 当然类似支付宝钱包这个Notification，我觉得是没有特别的必要自定义，此时更加推荐[使用官方的Notification风格](https://developer.android.com/training/notify-user/display-progress.html)。

## 使用

Gradle引用

> 相关类: [NotificationAdapter](https://github.com/Jacksgong/notification-adapter/blob/master/src/main/java/cn/dreamtobe/toolset/NotificationAdapter.java)

目前正在申请加入Jcenter中，在还没有通过前，在仓库: `https://dl.bintray.com/jacksgong/maven` 中。

```groovy
dependencies {
    compile 'cn.dreamtobe.toolset:notification-adapter:1.0.0'
}
```

通过方法或者颜色

```java
// 获取默认通知风格的Title的颜色
NotificationAdapter.getTitleColor(Context)
// 获取默认通知风格的Title的大小(Pixel)
NotificationAdapter.getTitleSize(Content)
// 获取默认通知风格的Text的颜色
NotificationAdapter.getTextColor(Context)
// 获取默认通知风格的Text的大小(Pixel)
NotificationAdapter.getTextSize(Content)
// 获取默认通知风格的Text的颜色
```


> 当然作为Notification，其实随着迭代演进已经有了很多很多很给力的Feature需要去适配，也欢迎大家到[Notification最佳实践](https://blog.dreamtobe.cn/2016/01/09/notification_best_practise/)拍砖，共同维护更好的Android环境。


## License

```
Copyright (C) 2017 Jacksgong(blog.dreamtobe.cn)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
