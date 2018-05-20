package com.ubercare.patientapi

import java.util.Date;

import grails.converters.JSON
import grails.rest.RestfulController

class PatientController extends RestfulController {
	
	def patientInfoService
	
	def patientEMRService
	
	static responseFormats = ['json', 'xml']

    def index() { }
	
	PatientController() {
		//super()
	}
	
	def updatePatientHospital() {
		println "here1"
		
		println request.JSON
	
		def resMap = [:]
		try {
			def patientID = request.JSON.patientID
			def url = request.JSON.url
			def name = request.JSON.name
			
	
			patientInfoService.updatePatientHospitalInfo(patientID, url, name)
			
			resMap = patientInfoService.listPatientHospitalInfo(patientID)
			
			
		} catch(Exception e) {
			resMap = ["status":"failure", "message":e.message]
		}
		render resMap as JSON
	}
	
	def getPatientDetails(){
		println "here1"
		
		println request.JSON
	
		def resMap = [:]
		try {
			def patientID = request.JSON.patientID
			
			
			
	
			//patientInfoService.updatePatientHospitalInfo(patientID, url, name)
			
			resMap = patientInfoService.listPatientHospitalInfo(patientID)
			
			
		} catch(Exception e) {
			resMap = ["status":"failure", "message":e.message]
		}
		render resMap as JSON
		
	}
	
	
	
	
	/**
	 * Returns all EMR access codes
	 * @return
	 */
	
	def getAllEMRAccessREasonCodes(){
		def resMap = [:]
		resMap = patientEMRService.listEMRAccessReasonCodes();
		render resMap as JSON;
		
	}
	
	def addEMRAccessReasonCode(){
		def resMap = [:]
		String reasonCode = request.JSON.reasonCode;
		resMap = patientEMRService.addEMRAccessReasonCode(reasonCode);
		render resMap as JSON;
		
	}
	
	
	def addEMRAccessAudit(){
		def resMap = [:]
		Long patientID = request.JSON.patientID
		Long userID = request.JSON.userID
		String reasonCode = request.JSON.reasonCode
		String deviceId = request.JSON.deviceId
		
		resMap = patientEMRServicec.addEMRAccessAudit(patientID,userID,reasonCode,deviceId)
		
	    
		
		render resMap as JSON;
	}
	
	def getEMRAccessAudit(){
		def responseMap = [:]
		Long patientID = request.JSON.patientID
		responseMap = patientEMRService.getEMRAccessReason(patientID)
		render responseMap as JSON
		
	}
	
	
}
