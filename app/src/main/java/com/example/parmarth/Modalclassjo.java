package com.example.parmarth;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Modalclassjo {
    String Company_Name,Job_Title,Job_Type,Job_Location,About_Company,Responsibilities,Skills,Perks;
    public Modalclassjo()
    {

    }
    public Modalclassjo(String Company_Name, String Job_Title, String Job_Type, String Job_Location,
                        String About_Company, String Responsibilities, String Skills, String Perks)
    {
        this.Company_Name=Company_Name;
        this.Job_Title=Job_Title;
        this.Job_Type=Job_Type;
        this.Job_Location=Job_Location;

        this.About_Company=About_Company;
        this.Responsibilities=Responsibilities;
        this.Skills=Skills;
        this.Perks=Perks;




    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getJob_Title() {
        return Job_Title;
    }

    public void setJob_Title(String job_Title) {
        Job_Title = job_Title;
    }

    public String getJob_Type() {
        return Job_Type;
    }

    public void setJob_Type(String job_Type) {
        Job_Type = job_Type;
    }

    public String getJob_Location() {
        return Job_Location;
    }

    public void setJob_Location(String job_Location) {
        Job_Location = job_Location;
    }

    public String getAbout_Company() {
        return About_Company;
    }

    public void setAbout_Company(String about_Company) {
        About_Company = about_Company;
    }

    public String getResponsibilities() {
        return Responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        Responsibilities = responsibilities;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getPerks() {
        return Perks;
    }

    public void setPerks(String perks) {
        Perks = perks;
    }



}


