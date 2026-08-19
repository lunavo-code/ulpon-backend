-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717697, '核心词管理', 2090075032473583618, 1, 'keyword', 'geo/keyword/index', 'N', 'Y', 'C', '0', '0', 'geo:keyword:list', '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '核心词管理菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717698, '核心词管理查询', 2090201273570717697, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keyword:query',        '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717699, '核心词管理新增', 2090201273570717697, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keyword:add',          '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717700, '核心词管理修改', 2090201273570717697, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keyword:edit',         '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717701, '核心词管理删除', 2090201273570717697, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keyword:remove',       '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090201273570717702, '核心词管理导出', 2090201273570717697, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keyword:export',       '#', 1761000000000000103, 1761100000000000001, sysdate, null, null, '');
