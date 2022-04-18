# 电商后台管理系统
**后台管理系统**：商家系统。维护着商品的发布、更迭等。维护着前台用户系统里面的数据。

前台用户系统和后台管理系统之间其实就是通过一个数据库的表来维护起来的。

**controller目录：**

- AdminServlet:  管理员模块，维护着可以登录后台管理系统的所有账号信息。
- UserServlet:  用户模块，维护前台用户系统里面的所有的客户信息
- GoodsServlet: 商品模块,商品的添加和删除操作
- OrderServlet: 订单模块，订单编辑，修改订单的状态等信息。

**service目录：**

- AdminService:  管理员模块的业务逻辑处理。
- UserService: 客户信息的业务逻辑处理
- GoodsService: 商品的添加和删除操作的业务逻辑处理
- OrderService: 订单编辑的业务逻辑处理。

**dao目录：**

- AdminDao:  获取与管理员相关的信息数据。
- UserDao: 获取与用户相关的信息数据。
- GoodsDaoe: 获取商品的相关数据。
- OrderDaoe: 获取订单的相关信息数据。

**bean目录：**

请求数据、响应数据和数据库数据的封装。bo对应请求数据的封装，vo对应响应数据的封装，pojo对应数据库数据的封装。

**utils目录：**

工具类目录

**filter目录：**

过滤器目录