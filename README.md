## Naïve Android App 简易的Android客户端

用WebView套了层壳，并且使用内嵌的安全DNS解析器防校园网的**DNS污染**导致的网站打不开，
不过直连的速度并没有变化，要快仍然需要额外的工具。

另请参阅：[“交大門”网站的相关主题](https://xjtu.men/t/topic/1440)

## 功能更新
* 对于所有非xjtu.men的链接，点击后自动用**系统的默认浏览器**打开
* ~~为了解决校园网的DNS污染，把xjtu.men到CloudFlare Edge的IP解析进行**硬编码**~~
* 摒弃IP的硬编码，内嵌基于[okhttp](https://square.github.io/okhttp/)的DNS over HTTPS (DoH)，[1.1.1.1](https://1.1.1.1/)。
[ ] 自动选择最快的DoH服务器，参考[DNSCrypt列表](https://github.com/DNSCrypt/dnscrypt-resolvers/)

## 欢迎反馈和贡献
由于鄙人Android开发还是3年之前学的，学艺不精，
如果不好用，欢迎在Github提Issue或者到“交大門”网站进行[反馈](https://xjtu.men/t/topic/1440)！

更加欢迎有兴趣的同学可以一起开发玩 :hugs:。

## License
MIT