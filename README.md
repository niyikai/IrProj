IrProj
======
version: 0.0 创建原始工程
version: 0.1 添加pinyin4j.jar


//////////////////////////////////////////////
把pinyin4j.jar添加到工程，使用方式在Demo.java里，非常简单；

需要给词典里的每一个term都运行这个工具获得他的拼音，以pinyin这个属性存到term对象里；

另外需要一个以pinyin作为key的dictionary，静态的就好，比如下面的，看看以什么方式保存。

——————————————————————————————————————————————
key	value

anhui - 安徽->俺会
daoshi - 导师
shiren - 诗人->是人->使人

value部分，是一个数组，这个数组以这些词的tfidf，从大到小排序；
——————————————————————————————————————————————


PS：也可以偷懒忽悠micheal，每个key对应value数组里的任意一个就行了，比如：
anhui - 安徽
daoshi - 导师
shiren - 诗人

到时如果查询‘是人’，查不到，那就显示 “你要找的是不是 诗人”

This is a commit test from Albert.