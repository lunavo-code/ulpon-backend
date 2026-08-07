-- ----------------------------
-- GEO智能优化系统 - 菜单初始化 SQL 脚本 (Ruoyi / MySQL 适用)
-- 本脚本包含：根目录菜单、二级子目录、三级功能菜单的完整 insert 语句。
-- 可以根据实际开发环境选择直接导入，或者手动微调 parent_id。
-- ----------------------------

-- 1. 获取一个安全的初始自增 ID 范围，或者直接依赖 LAST_INSERT_ID()。
-- 下面使用临时变量进行多层级父ID的定位。

-- ----------------------------
-- 一级主菜单：GEO智能优化
-- ----------------------------
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('GEO智能优化', '0', '1', 'geo', NULL, '0', '0', 'M', '0', '0', '', 'chart', 'admin', sysdate(), '', NULL, 'GEO智能优化根目录');

-- 获取主目录的插入ID
SELECT @root_id := LAST_INSERT_ID();


-- ----------------------------
-- 二级子目录 (目录类型 M)
-- ----------------------------

-- A. GEO诊断中心
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('GEO诊断中心', @root_id, '1', 'diagnostic', NULL, '0', '0', 'M', '0', '0', '', 'eye', 'admin', sysdate(), '', NULL, '评估与监测品牌在大模型中的曝光率');
SELECT @diag_id := LAST_INSERT_ID();

-- B. 策略词库仓
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('策略词库仓', @root_id, '2', 'strategy', NULL, '0', '0', 'M', '0', '0', '', 'search', 'admin', sysdate(), '', NULL, '大模型流量意图关键词筹备');
SELECT @strat_id := LAST_INSERT_ID();

-- C. 品牌配置仓
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('品牌配置仓', @root_id, '3', 'brand', NULL, '0', '0', 'M', '0', '0', '', 'setting', 'admin', sysdate(), '', NULL, '企业事实知识与视觉资产配置');
SELECT @brand_id := LAST_INSERT_ID();

-- D. 智能创作厂
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('智能创作厂', @root_id, '4', 'creative', NULL, '0', '0', 'M', '0', '0', '', 'edit', 'admin', sysdate(), '', NULL, 'AI写作核心流水线');
SELECT @create_id := LAST_INSERT_ID();

-- E. 一键宣发矩阵
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('一键宣发矩阵', @root_id, '5', 'publish', NULL, '0', '0', 'M', '0', '0', '', 'guide', 'admin', sysdate(), '', NULL, '多平台一键分发与官网SEO推流');
SELECT @pub_id := LAST_INSERT_ID();

-- F. 账户管理中心
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('账户管理中心', @root_id, '6', 'profile', NULL, '0', '0', 'M', '0', '0', '', 'user', 'admin', sysdate(), '', NULL, '财务对账、实名合规与会员特权');
SELECT @prof_id := LAST_INSERT_ID();


-- ----------------------------
-- 三级功能菜单 (菜单类型 C)
-- ----------------------------

-- A1. 曝光度诊断 (隶属: GEO诊断中心)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('曝光度诊断', @diag_id, '1', 'visibility', 'geo/visibility/index', '0', '0', 'C', '0', '0', 'geo:visibility:list', 'search', 'admin', sysdate(), '', NULL, '输入品牌词或关键词，分析其在主流大模型中的提及率与排名。');

-- A2. 检索截图库
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('检索截图库', @diag_id, '2', 'screenshot', 'geo/screenshot/index', '0', '0', 'C', '0', '0', 'geo:screenshot:list', 'image', 'admin', sysdate(), '', NULL, '查看模拟大模型检索交互的真实对话截图，保存曝光凭证。');

-- A3. 历史诊断日志
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('历史诊断日志', @diag_id, '3', 'diagnostic-log', 'geo/diaglog/index', '0', '0', 'C', '0', '0', 'geo:diaglog:list', 'log', 'admin', sysdate(), '', NULL, '追溯和对比历史的诊断操作日志，分析可见度变化时间线。');

-- A4. 流量与收录报表
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('流量与收录报表', @diag_id, '4', 'report', 'geo/report/index', '0', '0', 'C', '0', '0', 'geo:report:list', 'chart', 'admin', sysdate(), '', NULL, '多维度统计收录趋势、关键词排名和流量带来的 GEO 漏斗数据。');


-- B1. 核心词图谱 (隶属: 策略词库仓)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('核心词图谱', @strat_id, '1', 'keyword', 'geo/keyword/index', '0', '0', 'C', '0', '0', 'geo:keyword:list', 'tree', 'admin', sysdate(), '', NULL, '分类存储与集中管理企业核心品牌词、竞品词和意图词。');

-- B2. 搜索热度计
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('搜索热度计', @strat_id, '2', 'index-search', 'geo/index/index', '0', '0', 'C', '0', '0', 'geo:index:list', 'search', 'admin', sysdate(), '', NULL, '查询关键词在传统引擎与大模型检索中的搜索指数和竞争度。');

-- B3. AI智能拓词
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('AI智能拓词', @strat_id, '3', 'expand-ai', 'geo/expandai/index', '0', '0', 'C', '0', '0', 'geo:expandai:list', 'magic-stick', 'admin', sysdate(), '', NULL, '利用大模型语义关联，一键拓展关联的用户长尾提问词与场景词。');

-- B4. 意图拼装器
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('意图拼装器', @strat_id, '4', 'expand-manual', 'geo/expandmanual/index', '0', '0', 'C', '0', '0', 'geo:expandmanual:list', 'tool', 'admin', sysdate(), '', NULL, '设定前缀、核心词和后缀组合规则，批量拼装生成行业高契合度关键词。');


-- C1. 品牌知识库 (隶属: 品牌配置仓)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('品牌知识库', @brand_id, '1', 'knowledge', 'geo/knowledge/index', '0', '0', 'C', '0', '0', 'geo:knowledge:list', 'education', 'admin', sysdate(), '', NULL, '录入企业介绍、产品说明书、问答条目，用作 AI 写作的参考事实。');

-- C2. 品牌图库物料
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('品牌图库物料', @brand_id, '2', 'gallery', 'geo/gallery/index', '0', '0', 'C', '0', '0', 'geo:gallery:list', 'picture', 'admin', sysdate(), '', NULL, '存储与管理企业 Logo、产品宣传照等图片素材，自动嵌入成文插图。');


-- D1. 启迪标题生成 (隶属: 智能创作厂)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('启迪标题生成', @create_id, '1', 'title-gen', 'geo/title/index', '0', '0', 'C', '0', '0', 'geo:title:list', 'star', 'admin', sysdate(), '', NULL, '结合搜索收录习惯，一键为写作主题生成数十款高引用的优化标题。');

-- D2. 创作指令库
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('创作指令库', @create_id, '2', 'prompt', 'geo/prompt/index', '0', '0', 'C', '0', '0', 'geo:prompt:list', 'document', 'admin', sysdate(), '', NULL, '预设和自定义各类文体、角色的 Prompt 写作模板，统一调用。');

-- D3. 稿件分类
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('稿件分类', @create_id, '3', 'category', 'geo/category/index', '0', '0', 'C', '0', '0', 'geo:category:list', 'folder', 'admin', sysdate(), '', NULL, '对生成的文章进行业务模块或发布场景的分门别类管理。');

-- D4. 批量智写任务
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量智写任务', @create_id, '4', 'task', 'geo/task/index', '0', '0', 'C', '0', '0', 'geo:task:list', 'list', 'admin', sysdate(), '', NULL, '创建和查看大批量后台自动写作任务的执行进度与排队状况。');

-- D5. 稿件陈列室
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('稿件陈列室', @create_id, '5', 'article', 'geo/article/index', '0', '0', 'C', '0', '0', 'geo:article:list', 'document-copy', 'admin', sysdate(), '', NULL, '展示生成好的所有文章稿件，支持快速修改、导出、及一键宣发。');

-- D6. 爆文基因解码
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('爆文基因解码', @create_id, '6', 'copy-url', 'geo/copyurl/index', '0', '0', 'C', '0', '0', 'geo:copyurl:list', 'link', 'admin', sysdate(), '', NULL, '提取指定爆文 URL 的结构和用词偏好，用 AI 重塑生成高权重原创稿件。');

-- D7. 批量流量克隆
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('批量流量克隆', @create_id, '7', 'copy-batch', 'geo/copybatch/index', '0', '0', 'C', '0', '0', 'geo:copybatch:list', 'share', 'admin', sysdate(), '', NULL, '批量上传多条爆文链接或主题，矩阵化衍生裂变大批量伪原创文案。');


-- E1. 官网SEO卫士 (隶属: 一键宣发矩阵)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('官网SEO卫士', @pub_id, '1', 'publish-seo', 'geo/pubseo/index', '0', '0', 'C', '0', '0', 'geo:publish:seo', 'aim', 'admin', sysdate(), '', NULL, '推送文章到独立官网，自动更新站点地图并向搜索引擎和 AI 爬虫提交。');

-- E2. 矩阵号掌舵人
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('矩阵号掌舵人', @pub_id, '2', 'publish-self', 'geo/pubself/index', '0', '0', 'C', '0', '0', 'geo:publish:self', 'share', 'admin', sysdate(), '', NULL, '一键将选定稿件同步分发到绑定的微信公众号、百家号、小红书等自媒体。');

-- E3. B2B商贸阵地
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('B2B商贸阵地', @pub_id, '3', 'publish-b2b', 'geo/pubb2b/index', '0', '0', 'C', '0', '0', 'geo:publish:b2b', 'shop', 'admin', sysdate(), '', NULL, '将推广软文与商机发布到主流 B2B 商贸网、企业黄页平台。');

-- E4. 垂直媒体直通
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('垂直媒体直通', @pub_id, '4', 'publish-media', 'geo/pubmedia/index', '0', '0', 'C', '0', '0', 'geo:publish:media', 'message', 'admin', sysdate(), '', NULL, '向新闻网站与垂直行业门户群发企业通稿，用于获取高质量外链。');

-- E5. 领袖大V联盟
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('领袖大V联盟', @pub_id, '5', 'publish-kol', 'geo/pubkol/index', '0', '0', 'C', '0', '0', 'geo:publish:kol', 'promotion', 'admin', sysdate(), '', NULL, '向拥有高粉丝量、高权重的合作自媒体大 V 账号分发投稿。');


-- F1. 身份合验 (隶属: 账户管理中心)
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('身份合验', @prof_id, '1', 'auth', 'geo/auth/index', '0', '0', 'C', '0', '0', 'geo:auth:list', 'checked', 'admin', sysdate(), '', NULL, '提交并核验企业或个人实名认证，以符合法律合规发布要求。');

-- F2. 权益特权
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('权益特权', @prof_id, '2', 'rights', 'geo/rights/index', '0', '0', 'C', '0', '0', 'geo:rights:list', 'star', 'admin', sysdate(), '', NULL, '查看当前账户的会员等级、API 算力额度限制与套餐期限。');

-- F3. 财务账单
INSERT INTO sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
VALUES ('财务账单', @prof_id, '3', 'billing', 'geo/billing/index', '0', '0', 'C', '0', '0', 'geo:billing:list', 'money', 'admin', sysdate(), '', NULL, '统计点数/Tokens 的详细消耗变动日志及账户充值付款明细。');
