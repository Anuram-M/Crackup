package com.kumar.crackup.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kumar.crackup.model.ExamItem
import com.kumar.crackup.model.ExamTypes
import com.kumar.crackup.templates.BodyText
import com.kumar.crackup.templates.ExamCard
import com.kumar.crackup.templates.HeaderText

@Composable
fun ExamsInfoSection() {

    val exams = listOf(
        ExamItem(
            title = "UPSC - Union Public Service Commission",
            description = "India's premier constitutional body requiring officers for All-India and Central Civil Service",
            bullets = listOf(
                ExamTypes(
                    heading = "Major Exams:",
                    exams = "IAS, IPS, IFS, NDA, CDS, ESE, CAPF"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "Bachelor's Degree"
                ),
                ExamTypes(
                    heading = "Age:",
                    exams = "21-32 years"
                ),

                )
        ),
        ExamItem(
            title = "TNPSC - Tamil Nadu Public Service Commission",
            description = "State recruitment body for Tamil Nadu Government services.",
            bullets = listOf(
                ExamTypes(
                    heading = "Major Exams:",
                    exams = "Group 1, 2, 2A, 4"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "SSLC to Any Degree"
                ),
                ExamTypes(
                    heading = "Careers:",
                    exams = "Deputy Collector, DSP"
                ),

            )
        ),
        ExamItem(
            title = "SSC - Staff Selection Commission",
            description = "Recruits staff for central government ministries and departments.",
            bullets = listOf(
                ExamTypes(
                    heading = "Major Exams:",
                    exams = "CGL, CHSL, MTS, GD"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "10th / 12th / Degree"
                ),

            )
        ),
        ExamItem(
            title = "Banking - IBPS | SBI | RBI",
            description = "Banking exams for clerical and officer-level posts.",
            bullets = listOf(
                ExamTypes(
                    heading = "Major Exams:",
                    exams = "IBPS PO, SBI PO, RBI Grade B"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "Any Degree"
                ),
            )
        ),
        ExamItem(
            title = "NEET - Medical Entrance",
            description = "Single national exam for medical admissions.",
            bullets = listOf(
                ExamTypes(
                    heading = "Major Exams:",
                    exams = "MBBS, BDS, BAMS"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "10+2 (PCB)"
                ),
            )
        ),
        ExamItem(
            title = "JEE - Engineering Entrance",
            description = "Gateway to IITs, NITs and top engineering colleges.",
            bullets = listOf(
                ExamTypes(
                    heading = "Stages:",
                    exams = "JEE Main, JEE Advanced"
                ),
                ExamTypes(
                    heading = "Qualification:",
                    exams = "10+2 (PCB)"
                ),
            )
        ),

    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column() {
            HeaderText(text = "India's Major Competitive Exam Gateways", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp))
            BodyText(text = "Your complete guide to natianal and state level competitive examinations that open doors to government services, banking, medical and engineering careers in India.",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp))
            exams.forEach {
                ExamCard(it)
            }

        }

    }
}