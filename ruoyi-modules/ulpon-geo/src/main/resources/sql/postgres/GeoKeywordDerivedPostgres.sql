-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361986, '派生标题管理', 2090075032473583618, 1, 'keywordDerived', 'geo/keywordDerived/index', 'N', 'Y', 'C', '0', '0', 'geo:keywordDerived:list', '#', 1761000000000000103, 1761100000000000001, now(), null, null, '派生标题管理菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361987, '派生标题管理查询', 2090379090216361986, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keywordDerived:query',        '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361988, '派生标题管理新增', 2090379090216361986, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keywordDerived:add',          '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361989, '派生标题管理修改', 2090379090216361986, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keywordDerived:edit',         '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361990, '派生标题管理删除', 2090379090216361986, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keywordDerived:remove',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090379090216361991, '派生标题管理导出', 2090379090216361986, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'geo:keywordDerived:export',       '#', 1761000000000000103, 1761100000000000001, now(), null, null, '');
