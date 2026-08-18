-- ----------------------------------------------------------------------
-- GEO 智能管理系统 - 菜单初始化 SQL 脚本 (Ruoyi / MySQL 适用)
-- 本脚本包含：根目录菜单、二级子目录、三级功能菜单的完整 insert 语句。
-- 采用方案 A：专业 SaaS 极简命名风格
-- ----------------------------------------------------------------------

-- 一级主菜单：GEO 智能优化
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('GEO智能优化', '0', '1', 'geo', NULL, '0', '0', 'M', '0', '0', '', 'chart', 'admin', sysdate(), '', NULL, 'GEO智能优化根目录');

-- 获取主目录的插入ID
SELECT @root_id := LAST_INSERT_ID();


-- ----------------------------------------------------------------------
-- 二级子目录 (目录类型 M)
-- ----------------------------------------------------------------------

-- A. GEO 诊断中心
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('GEO诊断中心', @root_id, '1', 'diagnostic', NULL, '0', '0', 'M', '0', '0', '', 'eye', 'admin', sysdate(), '', NULL, '评估与监测品牌在大模型中的曝光率');
SELECT @diag_id := LAST_INSERT_ID();

-- B. 核心词库
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('核心词库', @root_id, '2', 'strategy', NULL, '0', '0', 'M', '0', '0', '', 'search', 'admin', sysdate(), '', NULL, '大模型流量意图关键词筹备');
SELECT @strat_id := LAST_INSERT_ID();

-- C. 品牌配置
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('品牌配置', @root_id, '3', 'brand', NULL, '0', '0', 'M', '0', '0', '', 'setting', 'admin', sysdate(), '', NULL, '企业事实知识与视觉资产配置');
SELECT @brand_id := LAST_INSERT_ID();

-- D. 内容工作坊
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('内容工作坊', @root_id, '4', 'creative', NULL, '0', '0', 'M', '0', '0', '', 'edit', 'admin', sysdate(), '', NULL, 'AI 写作核心流水线与稿件生成');
SELECT @create_id := LAST_INSERT_ID();

-- E. 宣发矩阵
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('宣发矩阵', @root_id, '5', 'publish', NULL, '0', '0', 'M', '0', '0', '', 'guide', 'admin', sysdate(), '', NULL, '多平台一键分发与官网 SEO 推流');
SELECT @pub_id := LAST_INSERT_ID();

-- F. 账户中心
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('账户中心', @root_id, '6', 'profile', NULL, '0', '0', 'M', '0', '0', '', 'user', 'admin', sysdate(), '', NULL, '财务对账、实名合规与会员套餐权益');
SELECT @prof_id := LAST_INSERT_ID();


-- ----------------------------------------------------------------------
-- 三级功能菜单 (菜单类型 C)
-- ----------------------------------------------------------------------

-- A1. 曝光监测 (隶属: GEO 诊断中心)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('曝光监测', @diag_id, '1', 'visibility', 'geo/visibility/index', '0', '0', 'C', '0', '0', 'geo:visibility:list', 'search', 'admin', sysdate(), '', NULL, '输入品牌词或关键词，分析其在主流大模型中的提及率与排名。');

-- A2. 竞品分析
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('竞品分析', @diag_id, '2', 'competitor', 'geo/competitor/index', '0', '0', 'C', '0', '0', 'geo:competitor:list', 'peoples', 'admin', sysdate(), '', NULL, '设定主要竞争对手品牌，追踪对比竞品在大模型问答中的被提及概率。');

-- A3. 诊断快照
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('诊断快照', @diag_id, '3', 'screenshot', 'geo/screenshot/index', '0', '0', 'C', '0', '0', 'geo:screenshot:list', 'image', 'admin', sysdate(), '', NULL, '查看模拟大模型检索交互的真实对话截图，保存曝光凭证。');

-- A4. 诊断历史
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('诊断历史', @diag_id, '4', 'diagnostic-log', 'geo/diaglog/index', '0', '0', 'C', '0', '0', 'geo:diaglog:list', 'log', 'admin', sysdate(), '', NULL, '追溯和对比历史的诊断操作日志，分析可见度变化时间线。');

-- A5. 收录报表
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('收录报表', @diag_id, '5', 'report', 'geo/report/index', '0', '0', 'C', '0', '0', 'geo:report:list', 'chart', 'admin', sysdate(), '', NULL, '多维度统计收录趋势、关键词排名和流量带来的 GEO 漏斗数据。');


-- B1. 词库管理 (隶属: 核心词库)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('词库管理', @strat_id, '1', 'keyword', 'geo/keyword/index', '0', '0', 'C', '0', '0', 'geo:keyword:list', 'tree', 'admin', sysdate(), '', NULL, '分类存储与集中管理企业核心品牌词、竞品词和意图词。');

-- B2. 热度指数
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('热度指数', @strat_id, '2', 'index-search', 'geo/index/index', '0', '0', 'C', '0', '0', 'geo:index:list', 'search', 'admin', sysdate(), '', NULL, '查询关键词在传统引擎与大模型检索中的搜索指数和竞争度。');

-- B3. AI 拓词
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('AI 拓词', @strat_id, '3', 'expand-ai', 'geo/expandai/index', '0', '0', 'C', '0', '0', 'geo:expandai:list', 'magic-stick', 'admin', sysdate(), '', NULL, '利用大模型语义关联，一键拓展关联的用户长尾提问词与场景词。');

-- B4. 规则拓词
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('规则拓词', @strat_id, '4', 'expand-manual', 'geo/expandmanual/index', '0', '0', 'C', '0', '0', 'geo:expandmanual:list', 'tool', 'admin', sysdate(), '', NULL, '设定前缀、核心词和后缀组合规则，批量拼装生成行业高契合度关键词。');


-- C1. 事实库 (隶属: 品牌配置)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('事实库', @brand_id, '1', 'knowledge', 'geo/knowledge/index', '0', '0', 'C', '0', '0', 'geo:knowledge:list', 'education', 'admin', sysdate(), '', NULL, '录入企业介绍、产品说明书、问答条目，用作 AI 写作的参考事实。');

-- C2. 官网同步
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('官网同步', @brand_id, '2', 'auto-learn', 'geo/autolearn/index', '0', '0', 'C', '0', '0', 'geo:autolearn:list', 'update', 'admin', sysdate(), '', NULL, '配置企业官方 URL，系统定时自动爬取并更新事实库内容。');

-- C3. 媒体资产
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('媒体资产', @brand_id, '3', 'gallery', 'geo/gallery/index', '0', '0', 'C', '0', '0', 'geo:gallery:list', 'picture', 'admin', sysdate(), '', NULL, '存储与管理企业 Logo、产品宣传照等图片素材，自动嵌入成文插图。');


-- D1. 标题生成 (隶属: 内容工作坊)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('标题生成', @create_id, '1', 'title-gen', 'geo/title/index', '0', '0', 'C', '0', '0', 'geo:title:list', 'star', 'admin', sysdate(), '', NULL, '结合搜索收录习惯，一键为写作主题生成数十款高引用的优化标题。');

-- D2. Prompt 模板
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('Prompt 模板', @create_id, '2', 'prompt', 'geo/prompt/index', '0', '0', 'C', '0', '0', 'geo:prompt:list', 'document', 'admin', sysdate(), '', NULL, '预设和自定义各类文体、角色的 Prompt 写作模板，统一调用。');

-- D3. 分类管理
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('分类管理', @create_id, '3', 'category', 'geo/category/index', '0', '0', 'C', '0', '0', 'geo:category:list', 'folder', 'admin', sysdate(), '', NULL, '对生成的文章进行业务模块或发布场景的分门别类管理。');

-- D4. 批量写作
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量写作', @create_id, '4', 'task', 'geo/task/index', '0', '0', 'C', '0', '0', 'geo:task:list', 'list', 'admin', sysdate(), '', NULL, '创建和查看大批量后台自动写作任务的执行进度与排队状况。');

-- D5. 稿件管理
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('稿件管理', @create_id, '5', 'article', 'geo/article/index', '0', '0', 'C', '0', '0', 'geo:article:list', 'document-copy', 'admin', sysdate(), '', NULL, '展示生成好的所有文章稿件，支持快速修改、导出、及一键宣发。');

-- D6. 宣发日历
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('宣发日历', @create_id, '6', 'calendar', 'geo/calendar/index', '0', '0', 'C', '0', '0', 'geo:calendar:list', 'date', 'admin', sysdate(), '', NULL, '以日历网格形式展示已计划及已发送的内容排期。');

-- D7. 单链接复刻
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('单链接复刻', @create_id, '7', 'copy-url', 'geo/copyurl/index', '0', '0', 'C', '0', '0', 'geo:copyurl:list', 'link', 'admin', sysdate(), '', NULL, '提取指定爆文 URL 的结构和用词偏好，用 AI 重塑生成高权重原创稿件。');

-- D8. 批量复刻
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量复刻', @create_id, '8', 'copy-batch', 'geo/copybatch/index', '0', '0', 'C', '0', '0', 'geo:copybatch:list', 'share', 'admin', sysdate(), '', NULL, '批量上传多条爆文链接或主题，矩阵化衍生裂变大批量伪原创文案。');


-- E1. 官网 SEO 推送 (隶属: 宣发矩阵)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('官网 SEO 推送', @pub_id, '1', 'publish-seo', 'geo/pubseo/index', '0', '0', 'C', '0', '0', 'geo:publish:seo', 'aim', 'admin', sysdate(), '', NULL, '推送文章到独立官网，自动更新站点地图并向搜索引擎和 AI 爬虫提交。');

-- E2. 自媒体分发
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('自媒体分发', @pub_id, '2', 'publish-self', 'geo/pubself/index', '0', '0', 'C', '0', '0', 'geo:publish:self', 'share', 'admin', sysdate(), '', NULL, '一键将选定稿件同步分发到绑定的微信公众号、百家号、小红书等自媒体。');

-- E3. B2B 平台发布
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('B2B 平台发布', @pub_id, '3', 'publish-b2b', 'geo/pubb2b/index', '0', '0', 'C', '0', '0', 'geo:publish:b2b', 'shop', 'admin', sysdate(), '', NULL, '将推广软文与商机发布到主流 B2B 商贸网、企业黄页平台。');

-- E4. 新闻通稿发布
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('新闻通稿发布', @pub_id, '4', 'publish-media', 'geo/pubmedia/index', '0', '0', 'C', '0', '0', 'geo:publish:media', 'message', 'admin', sysdate(), '', NULL, '向新闻网站与垂直行业门户群发企业通稿，用于获取高质量外链。');

-- E5. KOL 投稿
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('KOL 投稿', @pub_id, '5', 'publish-kol', 'geo/pubkol/index', '0', '0', 'C', '0', '0', 'geo:publish:kol', 'promotion', 'admin', sysdate(), '', NULL, '向拥有高粉丝量、高权重的合作自媒体大 V 账号分发投稿。');


-- F1. 实名认证 (隶属: 账户中心)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('实名认证', @prof_id, '1', 'auth', 'geo/auth/index', '0', '0', 'C', '0', '0', 'geo:auth:list', 'checked', 'admin', sysdate(), '', NULL, '提交并核验企业或个人实名认证，以符合法律合规发布要求。');

-- F2. 套餐权益
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('套餐权益', @prof_id, '2', 'rights', 'geo/rights/index', '0', '0', 'C', '0', '0', 'geo:rights:list', 'star', 'admin', sysdate(), '', NULL, '查看当前账户的会员等级、API 算力额度限制与套餐期限。');

-- F3. 算力变动明细
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('算力变动明细', @prof_id, '3', 'billing', 'geo/billing/index', '0', '0', 'C', '0', '0', 'geo:billing:list', 'money', 'admin', sysdate(), '', NULL, '统计点数/Tokens 的详细消耗变动日志及账户充值付款明细。');
