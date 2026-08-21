-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601793, '大模型配置', 2090631284417122305, 1, 'modelConfig', 'ai/modelConfig/index', 'N', 'Y', 'C', '0', '0', 'ai:modelConfig:list', '#', 1761000000000000103, 1761100000000000001, now(), null, null, '大模型配置菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601794, '大模型配置查询', 2090635263645601793, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelConfig:query',        '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601795, '大模型配置新增', 2090635263645601793, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelConfig:add',          '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601796, '大模型配置修改', 2090635263645601793, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelConfig:edit',         '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601797, '大模型配置删除', 2090635263645601793, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelConfig:remove',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263645601798, '大模型配置导出', 2090635263645601793, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelConfig:export',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
