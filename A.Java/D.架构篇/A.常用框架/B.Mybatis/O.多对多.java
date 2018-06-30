需求：
  查找用户购买的商品信息。
  用户和商品没有直接关系，可以通过
  用户-->订单-->订单明细-->商品信息。关联查询四个表，将商品信息查找出来。
  一个用户，多个订单，一个订单，多个明细，一个明细，一个商品信息。


在 User 类中，包含一个订单 List<Orders> 属性，
在 Orders 类中，包含一个 List<OrdersDetails> 属性，
在 OrdersDetails 类中，包含一个 Item 属性。
利用 <resultMap></resultMap>
将用户所购买的商品信息，保存在一个 User 结果集中。


多对多中，也可以自己定义一个返回的结果集，将查询结果返回
放在一个自定义的pojo中。

<resultMap></resultMap>
一般是对有特殊要求的功能需求设定的。
如，将鼠标移到用户头像上，显示其购买的商品信息。
需要将相同的用户信息过滤掉，则可以使用resultMap，只返回一个结果集，
一个结果集，返回多条商品信息。
<association></association>
<colletion></colletion>这两个就是针对于有特殊要求的情况下使用的，
一般情况不使用这个。