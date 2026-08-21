-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477762, '对话会话', 2090631284417122305, 1, 'chatSession', 'ai/chatSession/index', 'N', 'Y', 'C', '0', '0', 'ai:chatSession:list', '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '对话会话菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477763, '对话会话查询', 2090635264031477762, 1,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatSession:query',        '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477764, '对话会话新增', 2090635264031477762, 2,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatSession:add',          '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477765, '对话会话修改', 2090635264031477762, 3,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatSession:edit',         '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477766, '对话会话删除', 2090635264031477762, 4,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatSession:remove',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(2090635264031477767, '对话会话导出', 2090635264031477762, 5,  '#', '', 'N', 'Y', 'F', '0', '0', 'ai:chatSession:export',       '#', 1761000000000000103, 1761100000000000001, getdate(), null, null, '');
