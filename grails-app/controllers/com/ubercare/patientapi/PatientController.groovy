package com.ubercare.patientapi

import grails.converters.JSON
import grails.rest.RestfulController

class PatientController extends RestfulController {
	
	def patientInfoService
	
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
}
