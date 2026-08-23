-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604289, 'AI能力', 2090631284417122305, 1, 'capability', 'ai/capability/index', 'N', 'Y', 'C', '0', '0', 'ai:capability:list', '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, 'AI能力菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604290, 'AI能力查询', 2091209695598604289, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capability:query',        '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604291, 'AI能力新增', 2091209695598604289, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capability:add',          '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604292, 'AI能力修改', 2091209695598604289, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capability:edit',         '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604293, 'AI能力删除', 2091209695598604289, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capability:remove',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695598604294, 'AI能力导出', 2091209695598604289, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capability:export',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
