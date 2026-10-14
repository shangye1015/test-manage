package net.shangye.manage.test.testpreparesql;

/**
 * sqlite 执行预编译
 */
public class TestPrepareStatement {
//    @Override
//    public Integer updateRoleInfoUserPermissionList(List<RoleUserPermissionModel> roleUserPermissionModels) {
//        ConnectionPool.NotifyConnection notifyConnection = ConnectionPool.getConnection();
//        Connection conn = notifyConnection.getConnection();
//        PreparedStatement pstmt = null;
//        try {
//            conn.setAutoCommit(false);  // 开启事务管理，确保所有更新作为一个事务执行
//            for (RoleUserPermissionModel roleUserPermissionModel : roleUserPermissionModels) {
//                // 去除缓存
//                EhcacheUtil.getInstance().remove("MODULE_USER_ROLE_GET", "user_role_" + roleUserPermissionModel.getUserId());
//
//                if (StringUtils.isNotEmpty(roleUserPermissionModel.getUrrId())) {
//                    StringBuilder updateSql = new StringBuilder("UPDATE user_role_rel SET role_ids = ?, db_ids = ?, ");
//
//                    List<Object> parameters = new ArrayList<>();
//                    parameters.add(roleUserPermissionModel.getRoleIds());
//                    parameters.add(roleUserPermissionModel.getDbIds());
//
//                    if (StringUtils.isNotEmpty(roleUserPermissionModel.getStartTime())) {
//                        updateSql.append("start_time = ?, ");
//                        parameters.add(roleUserPermissionModel.getStartTime());
//                    }
//                    if (StringUtils.isNotEmpty(roleUserPermissionModel.getEndTime())) {
//                        updateSql.append("end_time = ?, ");
//                        parameters.add(roleUserPermissionModel.getEndTime());
//                    }
//
//                    updateSql.append("permission_set_status = ?, updator = ?, update_time = ? WHERE urr_id = ?;");
//
//                    parameters.add(roleUserPermissionModel.getPermissionSetStatus());
//                    parameters.add(roleUserPermissionModel.getUpdator());
//                    parameters.add(new Date().getTime()); // 直接使用参数化的方式设置当前时间
//                    parameters.add(roleUserPermissionModel.getUrrId());
//
//                    pstmt = conn.prepareStatement(updateSql.toString());
//
//                    // Set parameters
//                    for (int i = 0; i < parameters.size(); i++) {
//                        pstmt.setObject(i + 1, parameters.get(i));
//                    }
//
//                    pstmt.executeUpdate();
//                }
//            }
//            conn.commit(); // 提交事务
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    conn.rollback(); // 发生异常时回滚事务
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//            return 0;
//        } finally {
//            // 确保不改变原有finally块中的逻辑
//            ConnectionPool.releaseConnection(notifyConnection);
//        }
//        return 1; // 假设返回更新成功的数量
//    }

}


