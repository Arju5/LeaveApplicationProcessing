package com.sa4105.javaca2.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveBalance;
import com.sa4105.javaca2.model.User;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer> {

	@Query("SELECT lb FROM LeaveBalance lb where lb.user = :user")
	ArrayList<LeaveBalance> findLeaveBalanceByUser (@Param("user") User user);
	
	@Query("SELECT lb FROM LeaveBalance lb,LeaveType lt where lb.user.username = :username AND lb.leaveType.leaveTypeName = :leaveTypeName")
	LeaveBalance findLeaveBalanceByUsernameAndLeaveType (@Param("username") String username, @Param("leaveTypeName") String leaveTypeName);
	
	@Query("Select lb FROM LeaveBalance lb WHERE lb.user.Id = :userid AND lb.leaveType.Id = :leavetypeid")
	 LeaveBalance findLeaveBalance(@Param("leavetypeid") int leavetypeid, @Param("userid") int userid);	 
	
	@Query(value = "SELECT * FROM sa4105_java.leavebalances where leave_type_id=?0 AND user_id=?1", nativeQuery = true)
	LeaveBalance findLeaveBalanceByNativeQuery(Integer leavetypeid, Integer userid);
	
	@Query("SELECT lb FROM LeaveBalance lb where lb.user = :user")
	ArrayList<LeaveBalance> findLeaveBalanceByLeaveType (@Param("user") User user);

}
