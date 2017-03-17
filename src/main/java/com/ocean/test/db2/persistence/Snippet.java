package com.ocean.test.db2.persistence;

public class Snippet {
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper
	PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
	"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">
	<mapper namespace="com.ocean.test.db2.persistence.TsysPostMapper">
		<resultMap id="BaseResultMap" type="com.ocean.test.db2.domain.TsysPost">
		<result column="POST_ID" jdbcType="VARCHAR" property="postId" />
			<result column="POST_NAME" jdbcType="VARCHAR" property="postName" />
			<result column="REMARK" jdbcType="VARCHAR" property="remark" />
			<result column="POST_TYPE" jdbcType="VARCHAR" property="postType" />
			<result column="PARENT_POSTID" jdbcType="VARCHAR" property="parentPostid" />
		</resultMap>
	
		<!-- 用于select查询公用抽取的列 -->
		<sql id="Base_Column_List">
			POST_ID,POST_NAME,REMARK,POST_TYPE,PARENT_POSTID
		</sql>
		<insert id="insert" parameterType="com.ocean.test.db2.domain.TsysPost">
			INSERT INTO T_SYS_POST (
			POST_ID ,
			POST_NAME ,
			REMARK ,
			POST_TYPE ,
			PARENT_POSTID
			) VALUES (
	
			#{postId,jdbcType=VARCHAR} ,
	
			#{postName,jdbcType=VARCHAR} ,
	
			#{remark,jdbcType=VARCHAR} ,
	
			#{postType,jdbcType=VARCHAR} ,
	
			#{parentPostid,jdbcType=VARCHAR}
			)
		</insert>
		<update id="update" parameterType="com.ocean.test.db2.domain.TsysPost">
			UPDATE T_SYS_POST SET
			POST_NAME = #{postName,jdbcType=VARCHAR} ,
			REMARK = #{remark,jdbcType=VARCHAR} ,
			POST_TYPE = #{postType,jdbcType=VARCHAR} ,
			PARENT_POSTID = #{parentPostid,jdbcType=VARCHAR}
			WHERE
			POST_ID = #{postId,jdbcType=VARCHAR}
		</update>
		<delete id="delete" parameterType="java.lang.String">
			DELETE FROM T_SYS_POST WHERE
			POST_ID = #{postId,jdbcType=VARCHAR}
		</delete>
		<select id="selectDetailById" parameterType="java.lang.String"
			resultMap="BaseResultMap">
			select
			<include refid="Base_Column_List" />
			from T_SYS_POST WHERE
			POST_ID = #{postId,jdbcType=VARCHAR}
		</select>
		<!-- 实际业务需要增加的SQL语句 -->
		<select id="selectPageTsysPostByMap" resultMap="BaseResultMap"
			parameterType="map">
			SELECT
			<include refid="Base_Column_List" />
			FROM T_SYS_POST
			<where>
				<if test="postName != null and '' != postName">  <!-- #{} 是调用预处理，而${}则是字符串替换， 常用语排序里面 -->
					AND POST_NAME LIKE CONCAT(CONCAT('%', #{postName}),'%')
				</if>
				<if test="postType != null and '' != postType">
					AND POST_TYPE = #{postType}
				</if>
			</where>
		</select>
	
		<select id="selectPageTsysPost" resultMap="BaseResultMap">
			SELECT 
			<include refid="Base_Column_List" />
			FROM T_SYS_POST
		</select>
	</mapper>
	
	
}

