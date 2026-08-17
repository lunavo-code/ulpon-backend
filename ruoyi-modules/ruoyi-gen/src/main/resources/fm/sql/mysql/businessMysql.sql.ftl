<#-- @ftlvariable name="v" type="org.dromara.gen.domain.veriables.GenVariable" -->
-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[0]}, '${v.base.functionName}', ${v.option.parentMenuId}, 1, '${v.base.businessNameLower}', '${v.base.moduleName}/${v.base.businessNameLower}/index', 'N', 'Y', 'C', '0', '0', '${v.base.permissionPrefix}:list', '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '${v.base.functionName}菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[1]}, '${v.base.functionName}查询', ${v.column.table.menuIds[0]}, 1,  '#', '', 'N', 'Y', 'F', '0', '0', '${v.base.permissionPrefix}:query',        '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[2]}, '${v.base.functionName}新增', ${v.column.table.menuIds[0]}, 2,  '#', '', 'N', 'Y', 'F', '0', '0', '${v.base.permissionPrefix}:add',          '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[3]}, '${v.base.functionName}修改', ${v.column.table.menuIds[0]}, 3,  '#', '', 'N', 'Y', 'F', '0', '0', '${v.base.permissionPrefix}:edit',         '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[4]}, '${v.base.functionName}删除', ${v.column.table.menuIds[0]}, 4,  '#', '', 'N', 'Y', 'F', '0', '0', '${v.base.permissionPrefix}:remove',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
<#if v.option.enableExport>
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_dept, create_by, create_time, update_by, update_time, remark)
values(${v.column.table.menuIds[5]}, '${v.base.functionName}导出', ${v.column.table.menuIds[0]}, 5,  '#', '', 'N', 'Y', 'F', '0', '0', '${v.base.permissionPrefix}:export',       '#', 1761000000000000103, 1761100000000000001, sysdate(), null, null, '');
</#if>
