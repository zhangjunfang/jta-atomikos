package com.ocean.test.db1.persistence;

public class Snippet {
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
	"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">
	
	<mapper namespace="com.ocean.test.db1.persistence.TsysAppMapper">
		<resultMap id="BaseResultMap" type="com.hihsoft.db1.domain.TsysApp">
	        <result column="APP_ID" jdbcType="VARCHAR" property="appId"/>
	        <result column="APP_NAME" jdbcType="VARCHAR" property="appName"/>
	        <result column="APP_ICON" jdbcType="VARCHAR" property="appIcon"/>
	        <result column="ORDER_NO" jdbcType="DECIMAL" property="orderNo"/>
	        <result column="APP_PATH" jdbcType="VARCHAR" property="appPath"/>
	        <result column="APP_SHORTNAME" jdbcType="VARCHAR" property="appShortname"/>
		</resultMap>
		
		<!-- 用于select查询公用抽取的列 -->
	<sql id="Base_Column_List">
			APP_ID,APP_NAME,APP_ICON,ORDER_NO,APP_PATH,APP_SHORTNAME
	</sql>
	<insert id="insert"  parameterType="com.ocean.test.db1.domain.TsysApp">
	        INSERT INTO T_SYS_APP (
	        	APP_ID ,
	        	APP_NAME ,
	        	APP_ICON ,
	        	ORDER_NO ,
	        	APP_PATH ,
	        	APP_SHORTNAME 
	        ) VALUES (
	        
	        	#{appId,jdbcType=VARCHAR} ,
	        
	        	#{appName,jdbcType=VARCHAR} ,
	        
	        	#{appIcon,jdbcType=VARCHAR} ,
	        
	        	#{orderNo,jdbcType=DECIMAL} ,
	        
	        	#{appPath,jdbcType=VARCHAR} ,
	        
	        	#{appShortname,jdbcType=VARCHAR} 
	        )
	</insert>
	   <update id="update" parameterType="com.ocean.test.db1.domain.TsysApp">
	        UPDATE T_SYS_APP SET
		        APP_NAME = #{appName,jdbcType=VARCHAR} ,
		        APP_ICON = #{appIcon,jdbcType=VARCHAR} ,
		        ORDER_NO = #{orderNo,jdbcType=DECIMAL} ,
		        APP_PATH = #{appPath,jdbcType=VARCHAR} ,
		        APP_SHORTNAME = #{appShortname,jdbcType=VARCHAR} 
	        WHERE 
		        APP_ID = #{appId,jdbcType=VARCHAR} 
		</update>
	<delete id="delete" parameterType="java.lang.String">
	        DELETE FROM T_SYS_APP WHERE
		        APP_ID = #{appId,jdbcType=VARCHAR} 
	 </delete>
	 <select id="selectDetailById" parameterType="java.lang.String" resultMap="BaseResultMap">
	    select 
	    <include refid="Base_Column_List" />
	    from T_SYS_APP WHERE
		        APP_ID = #{appId,jdbcType=VARCHAR} 
	  </select>
	<!-- 实际业务需要增加的SQL语句 -->
	  <select id="selectPageTsysAppByMap" resultMap="BaseResultMap" parameterType="map">
		    SELECT 
		     <include refid="Base_Column_List" />
		        FROM T_SYS_APP T
		        <where>
			       <if test="appName != null and '' != appName">
			       	T.APP_NAME LIKE CONCAT(CONCAT('%', #{appName}),'%')    
			        </if>
			       <if test="appShortname != null and '' != appShortname">
			        	T.APP_SHORTNAME LIKE CONCAT(CONCAT('%', #{appShortname}),'%') 
			       </if>
		        </where>
	 </select>
	    
	<select id="selectPageTsysApp" resultMap="BaseResultMap" >
		    SELECT  
		    <include refid="Base_Column_List" />
		     FROM T_SYS_APP ORDER BY ORDER_NO ASC
	 </select>
	 
	 <select id="selectTsysApp" resultMap="BaseResultMap" >
		    SELECT  
		    <include refid="Base_Column_List" />
		     FROM T_SYS_APP A WHERE EXISTS(SELECT T.APP_ID FROM  T_SYS_MODULE T 
		     WHERE t.app_id= a.app_id and t.display=0 and t.parent_moduleid=0)
		     ORDER BY ORDER_NO ASC
	 </select>
	 
	 <select id="checkModuleOfSysApp" resultType="Integer" parameterType="java.lang.String">
		SELECT count(*) FROM  T_SYS_MODULE T
		     WHERE t.display=0 and t.parent_moduleid=0
		     and APP_ID = #{appId,jdbcType=VARCHAR} 
	 </select>
	 
	</mapper>
	
	
}

