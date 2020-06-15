# 设计说明

### 需求分析
期货合约的买入、卖出、改单、撤单、交易流水、持仓统计。

### 系统设计
4个http交易接口：
* /api/transaction/list
查看交易流水
* /api/transaction/create
交易下单
* /api/transaction/update
修改交易单
* /api/transaction/cancel
撤单

接口返回码：200正常、400非法参数、500系统异常。

controller、service、dao三层，单表t_transactions。


### 技术选型
* spring boot
* maven
* jdbcTemplate
* h2 database
* Hibernate validator