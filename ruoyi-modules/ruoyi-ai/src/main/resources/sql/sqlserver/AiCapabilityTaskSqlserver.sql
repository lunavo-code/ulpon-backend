-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339714, 'AI能力任务', 2090631284417122305, 1, 'capabilityTask', 'ai/capabilityTask/index', 'N', 'Y', 'C', '0', '0', 'ai:capabilityTask:list', '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, 'AI能力任务菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339715, 'AI能力任务查询', 2091209695204339714, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capabilityTask:query',        '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339716, 'AI能力任务新增', 2091209695204339714, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capabilityTask:add',          '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339717, 'AI能力任务修改', 2091209695204339714, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capabilityTask:edit',         '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339718, 'AI能力任务删除', 2091209695204339714, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capabilityTask:remove',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2091209695204339719, 'AI能力任务导出', 2091209695204339714, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:capabilityTask:export',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
