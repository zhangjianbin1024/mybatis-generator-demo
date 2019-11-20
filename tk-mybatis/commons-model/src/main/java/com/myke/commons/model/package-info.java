package com.myke.commons.model;

/**
 * 参考：https://www.cnblogs.com/wang-meng/p/5645405.html
 * <p>
 * <p>
 * PO(persistant object) 持久对象,PO 就是对应数据库中某个表中的一条记录
 * DO（Domain Object）领域对象,就是从现实世界中抽象出来的有形或无形的业务实体。一般和数据中的表结构对应。
 * TO(Transfer Object) ，数据传输对象,在不同的应用程序之间传输的对象
 * DTO（Data Transfer Object）数据传输对象，主要用于远程调用等需要大量传输对象的地方,DTO是服务器端和客户端进行通信的一个协议格式
 * VO(view object) 值对象，视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
 * BO(business object) 业务对象，主要作用是把业务逻辑封装为一个对象。这个对象可以包括一个或多个其它的PO对象，
 * POJO(plain ordinary java object) 简单无规则 java 对象,最基本的 Java Bean ，只有属性字段及 setter 和 getter 方法
 * DAO(data access object) 数据访问对象,它负持久层的操作。为业务层提供接口,此对象用于访问数据库,通常和 PO 结合使用
 * <p>
 * 用户发出请求（可能是填写表单），表单的数据在展示层被匹配为VO
 * 展示层把VO转换为服务层对应方法所要求的DTO，传送给服务层。
 * 服务层首先根据DTO的数据构造（或重建）一个DO，调用DO的业务方法完成具体业务
 * 服务层把DO转换为持久层对应的PO（可以使用ORM工具，也可以不用），调用持久层的持久化方法，把PO传递给它，完成持久化操作。
 */