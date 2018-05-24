1.账本如何验证
2.账户所有权问题
3.为什么记账(挖矿)
4.以谁的账本为准(共识机制)
5.哈希



------------------------------------------------
1.账本如何验证
例如,每个人的电脑中，都存在着相同的一个账本。如果某个机器将数据修改，则网络中就会存在真与假的数据。那么要以哪个账本为准？

第一次:
将账本数据进行Hash，得到摘要。如果摘要信息与其他机器的数据一致，则数据是准确的。

第二次: 
第N次，会有新的数据，得到的Hash值，会将上一次的Hash值和第N次的数据一起加入计算，如果数据一致，则说明这次数据是准确的，上一次数据也准确的。就这样不断循环下去，一次形成一个区块，最终形成区块链。

核对数据时，只需要核对最后一个区块的摘要信息

区块0<--区块1<--区块2<--区块N

-------------
2.账户所有权问题
非对称加密方式
账号也就是地址
转账的数据类似于
{
	"付款地址":"A22Fe3432334O",
	"收款地址":"ADe323KIeowID",
	"金额":"1btc"
}
密码-->私钥
私钥经过两个Hash处理，变成地址
地址:A22Fe3432334O
私钥:adIukredfgjsdfgUER
Hash(Hash(fun(adIukredfgjsdfgUER)))-->A22Fe3432334O


--------------
3.为什么记账(挖矿)
记账会有奖励，奖励的比特币相当于在发行。
工作量证明解决冲突



-------------
4.以谁的账本为准(共识机制)
两个节点同时完成工作量证明，使用谁的区块？
分叉 







---------------
5.哈希
Hash，一般翻译做“散列”，也有直接音译为“哈希”的，就是把任意长度的输入（又叫做预映射， pre-image），通过散列算法，变换成固定长度的输出。
这种转换是一种压缩映射，也就是，散列值的空间通常远小于输入的空间，不同的输入可能会散列成相同的输出，所以不可能从散列值来确定唯一的输入值。简单的说就是一种将任意长度的消息压缩到某一固定长度的消息摘要的函数。

特点:
5.1.同样的原始信息用同个哈希函数总能得到相同的摘要信息
5.2.原始信息任何微小的变化都会使哈希出面目全非的摘要信息
5.3.从摘要信息无法逆向推算原始信息

