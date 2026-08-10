# GEO 智能管理系统 —— RESTful API 接口清单

本清单明确了系统前后端交互的 HTTP 接口规范，各接口前缀统一默认为 `/geo`。

---

## 1. 📊 GEO 诊断中心 (Diagnostic Center)
模块根路径：`/geo/diagnostic`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **曝光监测** | `POST` | `/run` | `geo:diagnostic:run` | 发起单次诊断任务，放入异步队列 |
| | `GET` | `/task/{taskId}` | `geo:diagnostic:query` | 查询指定诊断任务的状态与实时进度 |
| | `GET` | `/result/{taskId}` | `geo:diagnostic:query` | 获取完成任务的平台诊断明细及引用结果 |
| **竞品分析** | `POST` | `/competitor/run` | `geo:competitor:run` | 发起竞品对比分析诊断任务 |
| | `GET` | `/competitor/list` | `geo:competitor:list` | 获取竞品声量对比及排位对比数据 |
| **诊断快照** | `GET` | `/screenshots` | `geo:screenshot:list` | 分页条件查询诊断历史截图相册列表 |
| **诊断历史**| `GET` | `/logs` | `geo:diaglog:list` | 分页条件查询诊断历史主列表 |
| | `DELETE` | `/logs/{taskIds}` | `geo:diaglog:remove` | 批量删除诊断历史 |
| **收录报表**| `GET` | `/dashboard/metrics`| `geo:report:query` | 获取仪表盘顶部的四个核心统计指标数据 |
| | `GET` | `/dashboard/charts` | `geo:report:query` | 获取Echarts图表数据（提及率、源站、转化漏斗等） |

---

## 2. 🎯 核心词库 (Keyword Strategy Center)
模块根路径：`/geo/keyword`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **词库管理** | `GET` | `/list` | `geo:keyword:list` | 分页多条件查询关键词列表 |
| | `POST` | `/add` | `geo:keyword:add` | 手动添加单个或批量导入关键词 |
| | `PUT` | `/update` | `geo:keyword:edit` | 修改关键词属性（如分类、手动指数） |
| | `DELETE` | `/remove/{ids}` | `geo:keyword:remove` | 批量删除关键词 |
| | `GET` | `/categories` | `geo:keyword:query` | 获取关键词词库多级分类树结构 |
| **热度指数** | `GET` | `/search-index` | `geo:index:query` | 输入词汇，查询传统与AI大模型的搜索指数 |
| **AI 拓词** | `POST` | `/expand/ai` | `geo:tools:expand` | 输入种子词和大模型参数，异步/同步拓词 |
| **规则拓词** | `POST` | `/expand/manual`| `geo:tools:manual` | 输入三栏拼接规则，执行组合拼装并返回预览 |

---

## 3. 🧠 品牌配置 (Brand Assets Library)
模块根路径：`/geo/brand`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **事实库** | `GET` | `/knowledge/list` | `geo:knowledge:list` | 分页条件获取事实库点明细 |
| | `POST` | `/knowledge/add` | `geo:knowledge:add` | 新增事实段落或QA知识点（触发向量化） |
| | `PUT` | `/knowledge/edit` | `geo:knowledge:edit` | 编辑修改知识点内容（触发重新向量化） |
| | `DELETE`| `/knowledge/remove/{ids}`| `geo:knowledge:remove`| 批量删除知识点 |
| **官网同步** | `GET` | `/sync/list` | `geo:autolearn:list` | 分页获取企业官网同步任务列表 |
| | `POST` | `/sync/add` | `geo:autolearn:add` | 新增企业官网自学习同步 URL 与间隔配置 |
| | `POST` | `/sync/run/{configId}`| `geo:autolearn:run` | 手动触发一次官网自动抓取与事实库更新 |
| **媒体资产** | `GET` | `/gallery/list` | `geo:gallery:list` | 分页查询 brand 图库图片列表 |
| | `POST` | `/gallery/upload` | `geo:gallery:add` | 上传单张/多张品牌图片到 OSS |
| | `PUT` | `/gallery/edit` | `geo:gallery:edit` | 更新图片的 ALT 标签及绑定的关键词 Tag |

---

## 4. 🎨 内容工作坊 (Smart Content Studio)
模块根路径：`/geo/creative`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **标题生成** | `POST` | `/title/generate` | `geo:title:generate` | 基于大模型，输入主旨批量生成优化标题 |
| **Prompt 模板** | `GET` | `/prompt/list` | `geo:prompt:list` | 获取创作指令 Prompt 模板网格列表 |
| | `POST` | `/prompt/add` | `geo:prompt:add` | 新增写作指令模版 |
| **批量写作** | `POST` | `/task/submit` | `geo:task:add` | 发起多线程批量异步写作任务 |
| | `GET` | `/task/list` | `geo:task:list` | 分页获取批量写作的队列状态与进度 |
| **稿件管理** | `GET` | `/article/list` | `geo:article:list` | 分页条件检索已生成的稿件列表 |
| | `GET` | `/article/{id}` | `geo:article:query` | 查看单篇稿件的 HTML 富文本内容 |
| | `PUT` | `/article/edit` | `geo:article:edit` | 在线编辑器二次修改并保存文章 |
| | `GET` | `/article/geo-score/{id}`| `geo:article:query`| 获取单篇文章的 GEO 评分卡得分及幻觉分析 |
| **宣发日历** | `GET` | `/calendar/events`| `geo:calendar:list` | 获取计划宣发和已宣发的时间线日程数据 |
| **流量裂变舱** | `POST` | `/replicate/url` | `geo:replicate:url` | 传入爆文 URL，异步解析并复刻新文章 |

---

## 5. 🚀 宣发矩阵 (Publishing Matrix)
模块根路径：`/geo/publish`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **宣发绑定** | `GET` | `/accounts` | `geo:publish:list` | 获取已绑定宣发渠道账户列表（按平台分类） |
| | `POST` | `/accounts/bind` | `geo:publish:add` | 新增绑定独立站、自媒体或 B2B 渠道账户 |
| | `DELETE`| `/accounts/unbind/{id}`| `geo:publish:remove`| 解除绑定指定发布账号 |
| **宣发控制与监控**| `POST` | `/dispatch` | `geo:publish:dispatch` | 一键将选定文章批量分发至指定渠道 |
| | `GET` | `/logs` | `geo:publish:list` | 分页查询分发记录表，查看目标链接与收录状态 |
| | `POST` | `/check-index/{logId}`| `geo:publish:dispatch` | 对指定发布链接手动发起爬虫/大模型收录状态检测 |

---

## 6. 👤 账户中心 (Account Control Center)
模块根路径：`/geo/account`

| 功能模块 | 请求方法 | 接口路径 | 鉴权字符 | 功能简述 |
| :--- | :--- | :--- | :--- | :--- |
| **身份核验** | `POST` | `/auth/submit` | `geo:auth:add` | 提交实名认证申请（支持个人/企业） |
| | `GET` | `/auth/status` | `geo:auth:query` | 获取当前用户的实名认证状态与反馈 |
| **套餐权益** | `GET` | `/rights` | `geo:rights:query` | 获取用户当前套餐级别、各功能日用额度余量 |
| **算力变动明细** | `GET` | `/billing/points`| `geo:billing:query` | 查询算力点数变化消费明细账单 |
| | `GET` | `/billing/balance`| `geo:billing:query` | 查询现金钱包充值与服务订购消费流水 |
