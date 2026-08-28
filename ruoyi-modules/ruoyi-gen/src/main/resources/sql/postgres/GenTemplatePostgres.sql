-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125825, '代码生成模板', 1761400000000000003, 1, 'template', 'gen/template/index', 'N', 'Y', 'C', '0', '0', 'gen:template:list', '#', 1761000000000000103, 1761100000000000001, now(), null, null, '代码生成模板菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125826, '代码生成模板查询', 2093217626498125825, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'gen:template:query',        '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125827, '代码生成模板新增', 2093217626498125825, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'gen:template:add',          '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125828, '代码生成模板修改', 2093217626498125825, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'gen:template:edit',         '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125829, '代码生成模板删除', 2093217626498125825, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'gen:template:remove',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2093217626498125830, '代码生成模板导出', 2093217626498125825, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'gen:template:export',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
