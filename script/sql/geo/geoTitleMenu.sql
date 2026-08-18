-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131330, 'GEO标题生成', 1761400000000011810, 1, 'geoTitle', 'geo/geoTitle/index', 'N', 'Y', 'C', '0', '0', 'geo:geoTitle:list', '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, 'GEO标题生成菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131331, 'GEO标题生成查询', 2086386803996131330, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:geoTitle:query',        '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131332, 'GEO标题生成新增', 2086386803996131330, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:geoTitle:add',          '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131333, 'GEO标题生成修改', 2086386803996131330, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:geoTitle:edit',         '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131334, 'GEO标题生成删除', 2086386803996131330, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:geoTitle:remove',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2086386803996131335, 'GEO标题生成导出', 2086386803996131330, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:geoTitle:export',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
