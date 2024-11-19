package com.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
//=================================
import com.company.Company;
import com.company.CompanyService;
import com.company.CompanyRepository;
import com.job.Job;
import com.job.JobService;
import com.job.JobRepository;
import com.applicant.Applicant;
import com.applicant.ApplicantService;
import com.applicant.ApplicantRepository;
import com.interview.Interview;
import com.interview.InterviewService;
import com.interview.InterviewRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final CompanyService companyService=new CompanyService(new CompanyRepository());
    private static final JobService jobService=new JobService(new JobRepository());
    private static final InterviewService interviewService=new InterviewService(new InterviewRepository());
    private static final ApplicantService applicantService=new ApplicantService(new ApplicantRepository());
//=========================================================================================================
    public static void main(String[] args) {
        System.out.println("**********欢迎使用招聘系统**********");
        System.out.println("招聘系统说明：");//读取文件
        try{
            FileReader fr=new FileReader("E:\\idea file\\hire system\\说明.txt");
            BufferedReader br=new BufferedReader(fr);//建立buffer
            String line=br.readLine();//读一行
            while(line!=null){//循环
                System.out.println(line);
                line=br.readLine();
            }
            br.close();//关闭buffer对象
            fr.close();//关闭文件对象

        }catch(IOException e){//异常处理
            System.out.println("文件读取出错");

        }
        //============================选择界面
        boolean flag=false;
        do{
            System.out.println("*************************");
            System.out.println("1. 公司信息管理");
            System.out.println("2. 招聘职位信息管理");
            System.out.println("3. 应聘者信息管理");
            System.out.println("4. 面试管理");
            System.out.println("5. 退出");
            System.out.print("请选择操作：");
            Scanner scanner=new Scanner(System.in);
            String input=scanner.nextLine();
            int choice=Integer.parseInt(input);
            switch(choice){
                case 1:
                    manageCompanies(scanner);
                    break;
                case 2:
                    manageJob(scanner);
                    break;
                case 3:
                    manageApplicant(scanner);
                    break;
                case 4:
                    manageInterviews(scanner);
                    break;
                case 5:
                    System.out.println("谢谢使用再见！");
                    flag=true;
                    break;
                default:
                    System.out.println("错误指令");
            }

        }while(!flag);


    }
    //公司信息管理==========================
    private static void manageCompanies(Scanner scanner){
        System.out.println("*************************");
        System.out.println("公司信息管理");
        System.out.println("1.查看所有公司");
        System.out.println("2.添加公司");
        System.out.println("请选择操作：");
        String input=scanner.nextLine();
        int choice=Integer.parseInt(input);
        //-------------------
        switch(choice){
            case 1:
                List<Company> companies=companyService.getAllCompanies();
                for(Company company:companies){
                    System.out.println(company.getCompany_id()+" - "+company.getCompany_name()+" - "+company.getCompany_address());
                }
                break;
            case 2:
                System.out.println("请输入公司id：");
                int id=scanner.nextInt();
                scanner.nextLine();
                System.out.println("请输入公司名称：");
                String name=scanner.nextLine();
                System.out.println("请输入公司地址：");
                String address=scanner.nextLine();
                Company company=new Company();
                company.setCompany_id(id);
                company.setCompany_name(name);
                company.setCompany_address(address);
                companyService.addCompany(company);
                System.out.println("公司添加成功！");
                break;
            default:
                System.out.println("操作错误");

        }
    }
    //招聘职位信息管理=========================
    private static void manageJob(Scanner scanner){
        System.out.println("*************************");
        System.out.println("招聘职位信息管理");
        System.out.println("1.查看所有工作信息");
        System.out.println("2.添加职位");
        System.out.println("请选择操作：");
        String input=scanner.nextLine();
        int choice=Integer.parseInt(input);
        //-----------------1
        switch(choice){
            case 1:
                List<Job> jobs = jobService.getAllJobs();
                for (Job job : jobs) {
                    System.out.println(job.getJob_company().getCompany_name()+" - "+job.getJob_id()+" - "+job.getJob_name() + " - " + job.getJob_description());
                }
                break;
            case 2:
                System.out.println("请输入职位id：");
                int id=scanner.nextInt();
                System.out.println("请输入对应公司id：");
                int company_id=scanner.nextInt();
                scanner.nextLine();
                System.out.print("请输入职位名称：");
                String name = scanner.nextLine();
                System.out.print("请输入职位描述：");
                String description = scanner.nextLine();
                Company company = companyService.getAllCompanies().stream()
                        .filter(a -> a.getCompany_id() == company_id)
                        .findFirst().orElse(null);
                if(company!=null) {
                    Job job = new Job();
                    job.setJob_company(company);
                    job.setJob_id(id);
                    job.setJob_name(name);
                    job.setJob_description(description);
                    jobService.addJob(job);
                    System.out.println("职位添加成功！");
                }else{
                    System.out.println("没有此公司");
                }
                break;
            default:
                System.out.println("操作错误");
        }

    }
    //应聘者信息管理===========================
    private static void manageApplicant(Scanner scanner){
        System.out.println("*************************");
        System.out.println("应聘者信息管理：");
        System.out.println("1.查看所有应聘者信息");
        System.out.println("2.添加应聘者信息");
        System.out.println("3.查找应聘者的应聘情况");
        System.out.println("请选择操作");
        String input=scanner.nextLine();
        int choice=Integer.parseInt(input);
        //--------------------
        switch(choice){
            case 1:
                List<Applicant> applicants=applicantService.getApplicants();
                for(Applicant applicant:applicants){
                    System.out.println(applicant.getApplicant_id()+" - "+applicant.getApplicant_name()+" - "+applicant.getApplicant_email());
                }
                break;
            case 2:
                System.out.println("请输入应聘者id：");
                int id=scanner.nextInt();
                scanner.nextLine();
                System.out.print("请输入应聘者姓名：");
                String name = scanner.nextLine();
                System.out.print("请输入应聘者邮箱：");
                String email = scanner.nextLine();
                System.out.print("请输入应聘者简历：");
                String resume = scanner.nextLine();

                Applicant applicant = new Applicant();
                applicant.setApplicant_id(id);
                applicant.setApplicant_name(name);
                applicant.setApplicant_email(email);
                applicant.setApplicant_resume(resume);
                applicantService.addApplicant(applicant);
                System.out.println("应聘者添加成功！");
                break;
            case 3:
                System.out.print("输入应聘者 ID: ");
                int applicantId = scanner.nextInt();
                List<Interview> interviews = applicantService.getInterviewDetailsForApplicant(applicantId);
                for (Interview interview : interviews) {
                    System.out.println("面试 ID: " + interview.getId());
                    System.out.println("面试备注: " + interview.getInterview_notes());
                    System.out.println("是否被录用: " + (interview.isInterview_isHired() ? "是" : "否"));
                    System.out.println();
                }
                break;
            default:
                System.out.println("操作错误");
        }

    }
    //面试管理
    private static void manageInterviews(Scanner scanner){
        System.out.println("*************************");
        System.out.println("面试信息管理：");
        System.out.println("1.查看所有面试信息");
        System.out.println("2.添加面试信息");
        System.out.println("请选择操作");
        String input=scanner.nextLine();
        int choice=Integer.parseInt(input);
        //--------------------
        switch(choice){
            case 1:
                List<Interview> interviews=interviewService.getAllInterviews();
                for (Interview interview : interviews) {
                    System.out.println("应聘者：" + interview.getApplicant_interview().getApplicant_name() +
                            " - 职位：" + interview.getJob_interview().getJob_name() +
                            " - 备注：" + interview.getInterview_notes() +
                            " - 是否录用：" + (interview.isInterview_isHired() ? "是" : "否"));
                }
                break;
            case 2:
                System.out.print("请输入本次应聘的场次ID：");
                int interviewId=scanner.nextInt();
                System.out.print("请输入应聘者ID：");
                int applicantId=scanner.nextInt();
                System.out.print("请输入职位ID：");
                int jobId=scanner.nextInt();
                scanner.nextLine();
                System.out.print("请输入面试备注：");
                String notes = scanner.nextLine();
                System.out.print("是否录用（true/false）：");
                input = scanner.nextLine();
                boolean isHired = Boolean.parseBoolean(input);

                Applicant applicant = applicantService.getApplicants().stream()
                        .filter(a -> a.getApplicant_id() == applicantId)
                        .findFirst().orElse(null);
                Job job = jobService.getAllJobs().stream()
                        .filter(j -> j.getJob_id() == jobId)
                        .findFirst().orElse(null);
                if (applicant != null && job != null) {
                    Interview interview = new Interview();

                    interview.setId(interviewId);
                    interview.setApplicant_interview(applicant);
                    interview.setJob_interview(job);
                    interview.setInterview_notes(notes);
                    interview.setInterview_isHired(isHired);
                    interviewService.addInterview(interview);
                    System.out.println("面试添加成功！");
                } else {
                    System.out.println("应聘者或职位未找到，请重试。");
                }
                break;
            default:
                System.out.println("操作错误");
        }

    }

}