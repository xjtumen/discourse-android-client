# Android Client for Discourse 

You can use this Android client to access [xjtu.live](https://xjtu.live), a Discourse powered forum-like communication platform for [XJTU](https://en.wikipedia.org/wiki/Xi%27an_Jiaotong_University). 

Or you can use it as a template by simply replacing `xjtu.live` with the URL of any website you like (also remember to change the launcher icon).

## Features
* (optional) support embedded secure DNS (DNS over HTTPS) using okhttp
* support specifying which domains / URLs to open in system's default browser
* support uploading (no storage permission required)
[ ] support (partial / full-site) cache 
[ ] automatically / periodically fetch new posts
[ ] automatically connect to the optimal(closest) CDN edge server

## Why not use off-the-shelf browser like Chrome / Firefox
Considering Google Play is inaccessible and even not pre-installed  in **some** regions of the world,  Chrome and Firefox are not readily available and hard to obtain, many people are accustomed to open a service by a single click on an icon in their launcher. 

For users in other regions, as Discourse supports the [Progressive Web Application](https://meta.discourse.org/t/progressive-web-application-pwa/79217) feature, you can add a launcher icon by simply clicking 'Install APP' in Chrome or in [Safari](https://meta.discourse.org/t/discourse-now-works-as-a-pwa-in-ios/146346).

## Contribution and feedback
New contributions are very welcomed. :hugs:

If you have any problems, feel free to file an issue or discuss in the related posts on [xjtu.live](https://xjtu.live/t/topic/1440).

# Discourse Android客户端
使用Kotlin开发，用WebView套了层壳，并且使用内嵌的安全DNS解析器解决校园网**DNS污染**导致网站打不开的问题。

使用客户端和直接使用浏览器相比，直连的速度并没有变化，要快仍然需要额外的工具。

经过测试，Chrome会默认使用QUIC连接，相比于HTTP/2 & TLS1.3的Firefox感觉上要快一点。

另请参阅：[“交大門”网站的相关主题](https://xjtu.live/t/topic/1440)

## 功能更新
* 对于所有非xjtu.live的链接，点击后自动用**系统的默认浏览器**打开
* ~~为了解决校园网的DNS污染，把xjtu.live到CloudFlare Edge的IP解析进行**硬编码**~~
* 内嵌基于okhttp的DNS over HTTPS (DoH)，[1.1.1.1](https://1.1.1.1/)。
  [ ] 自动选择最快的DoH服务器，参考[DNSCrypt列表](https://github.com/DNSCrypt/dnscrypt-resolvers/)
  [ ] 自动选择最快的CloudFlare Edge服务器，加速网站访问
* 由于网络是中国互联网用户的一个很大的瓶颈，将增加以下功能，优化用户体验：
  [ ] 使用缓存，避免每次打开网页过慢。（考虑到目前内容较少，也许可以采用**全站**本地存储？）
  [ ] 自动获取新内容，无需用户人工刷新
* [ ] 支持上传（也许无需打开访问/storage/emulated/0/的权限，调用接口即可）

## 欢迎反馈和贡献
欢迎在Github提Issue或者到“交大門”网站进行[反馈](https://xjtu.live/t/topic/1440)！

更加欢迎有兴趣的同学可以一起开发玩 :hugs:。

## License
MIT
