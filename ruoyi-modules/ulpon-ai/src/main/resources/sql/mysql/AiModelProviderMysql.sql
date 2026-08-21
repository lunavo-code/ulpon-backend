-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663810, '大模型提供商', 2090631284417122305, 1, 'modelProvider', 'ai/modelProvider/index', 'N', 'Y', 'C', '0', '0', 'ai:modelProvider:list', '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '大模型提供商菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663811, '大模型提供商查询', 2090635263452663810, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelProvider:query',        '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663812, '大模型提供商新增', 2090635263452663810, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelProvider:add',          '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663813, '大模型提供商修改', 2090635263452663810, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelProvider:edit',         '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663814, '大模型提供商删除', 2090635263452663810, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelProvider:remove',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635263452663815, '大模型提供商导出', 2090635263452663810, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:modelProvider:export',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
