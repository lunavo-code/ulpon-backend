-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249922, '对话消息', 2090631284417122305, 1, 'chatMessage', 'ai/chatMessage/index', 'N', 'Y', 'C', '0', '0', 'ai:chatMessage:list', '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '对话消息菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249923, '对话消息查询', 2090635264199249922, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatMessage:query',        '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249924, '对话消息新增', 2090635264199249922, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatMessage:add',          '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249925, '对话消息修改', 2090635264199249922, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatMessage:edit',         '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249926, '对话消息删除', 2090635264199249922, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatMessage:remove',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264199249927, '对话消息导出', 2090635264199249922, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatMessage:export',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
