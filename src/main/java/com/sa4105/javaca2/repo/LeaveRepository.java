package com.sa4105.javaca2.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sa4105.javaca2.model.Leave;
import com.sa4105.javaca2.model.LeaveStatus;


public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	
	@Query("Select l from Leave l where l.leaveStatus = :apply OR l.leaveStatus = :update")
	List<Leave> findStatusApplyUpdate(@Param("apply") LeaveStatus apply, @Param("update") LeaveStatus update);
	
	/*
	 * @Query("Select l from Leave l where l.leaveStatus = :statusid") public
	 * List<Leave> findByleaveStatus(@Param("statusid") Integer statusid);
	 */
	
	/*
	 * @Query("Select l from Leave l where user_id = :uid") List<Leave>
	 * findLeaveByUserid(@Param("uid") Integer uid);
	 */
	 
	 @Query("Select l from Leave l where l.user.username = :username")
	 List<Leave> findLeaveByUserName(@Param("username") String username);
	 
//	 @Query(value = "Select * FROM Leave l, User u WHERE l.user_id = u.id AND u.id = :rid", nativeQuery = true)
//	 public List<Leave> findLeaveRoleid(@Param("rid") Integer rid);
	 
	 @Query("Select l FROM Leave l WHERE l.user.role.Id = :rid")
	 List<Leave> findLeaveByRoleid(@Param("rid") Integer rid);
	 
	 @Query("Select l FROM Leave l WHERE l.user.role.roleName = :roleName")
	 List<Leave> findLeaveByRoleid(@Param("roleName") String roleName);
	 
	 @Query("Select l FROM Leave l WHERE l.user.firstName = :firstName AND l.user.lastName = :lastName")
	 List<Leave> findLeaveByfirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	 
	 @Query("Select l FROM Leave l WHERE l.leaveType.leaveTypeName = :leaveTypeName")
	 List<Leave> findLeaveByLeaveType(@Param("leaveTypeName") String leaveTypeName);
		/*
		 * @Query("Select l FROM Leave l WHERE l.leave.leaveStatus = :leaveStatus")
		 * List<Leave> findLeaveByLeaveStatus(@Param("leaveStatus") String leaveStatus);
		 */

	 
	 @Query("Select l FROM Leave l WHERE l.leaveStartDate >= :startdate AND l.leaveEndDate <= :enddate AND (l.leaveStatus LIKE 'APPROVED' OR l.leaveStatus LIKE 'REJECTED')")
	 List<Leave> findLeaveDuringLeavePeriod(@Param("startdate") LocalDate startdate, @Param("enddate") LocalDate enddate);


		/*
		 * @Query("Select l FROM Leave l WHERE l.leaveStatus = :leaveStatus")
		 * List<Leave> findLeaveByLeaveStatus(@Param("leaveStatus") LeaveStatus
		 * leaveStatus);
		 */
	 
	 @Query("Select count(l) FROM Leave l WHERE l.leaveStatus = :leaveStatus")
	    int countLeaveByLeaveStatus(@Param("leaveStatus") LeaveStatus leaveStatus);

	 @Query("Select l FROM Leave l WHERE l.applyLeaveDate >= :applyDate AND l.leaveStatus LIKE 'APPLIED'")
	 List<Leave> findLeavebyApplyDate(@Param("applyDate") LocalDate applyDate);
	 
	 @Query("Select l FROM Leave l WHERE l.leaveStatus LIKE 'APPROVED' OR l.leaveStatus LIKE 'REJECTED'")
	 List<Leave> findLeaveHistory();
	 
	 
}
