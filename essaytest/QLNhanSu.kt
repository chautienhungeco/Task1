package com.apero.essaytest

//Demo xây dựng hệ thống quản lý thành viên trong một đội
//phát triển phần mềm



//Lớp cha person, constructor chính
open class Person(val name: String, val age: Int){
    open fun introduce(){
        //giới thiệu nhân viên
        println("Xin chào mọi người, mình là $name, $age tuổi")
    }
}
//lớp con kế thừa Person
open class Employee(name: String, age: Int, val employeeId: String, val salary: Double) : Person(name,age){
    // - ghi đè override hàm introduce để thêm các chi tiết Id và lương
    override fun introduce() {
        println("Tôi là $name nhân viên mới voi ID: $employeeId và mức lương: $salary")
    }
    //constructor phụ và gọi const chính
    constructor(name: String, age: Int, employeeId: String) : this(name, age, employeeId, 0.0)
}
//tạo data class lưu dl công việc
data class Task(val taskId: String, val taskName: String, var isCompleted: Boolean = false)

//tạo interface có hàm trừu tương (abstract fun) để gán công việc
interface TaskAssignable{
    fun assignTask(task: Task)
}

//tạo abstract class TeamMember kế thua class Employee
abstract class TeamMember(name: String, age: Int, employeeId: String, salary: Double) : Employee(name, age, employeeId, salary), TaskAssignable{
    //thuộc tính trừu tượng role
    abstract val role: String
    //hàm trừu trượng work()
    abstract fun work()
    //thuộc tính assignedTasks ds task có thể thay đổi
    val assignedTasks: MutableList<Task> = mutableListOf()
    override fun assignTask(task: Task) {
        println("Giao việc: '${task.taskName}' cho $name")
        assignedTasks.add(task)
    }
}

// class developer kế thừa TeamMember
class Developer(name: String,age: Int, employeeId: String, salary: Double, val favouriteLanguage: String) :
        TeamMember(name, age, employeeId, salary){
    override val role: String = "Developer"
    //ghi đè hàm work() mô tả cv cụ thể
    override fun work(){
        println("Ngôn ngữ yêu thích: $favouriteLanguage")
    }
        }

//kế thừa TeamMember
class Manager(name: String, age: Int, employeeId: String, salary: Double ) :
        TeamMember(name, age, employeeId, salary){
    override val role: String = "Manger"
    //ghi đè hàm work() mô tả cv
    override fun work(){
        println("Tôi đang quản lý dự án và các tv trong team")
    }
        }

//đối tượng singleton Company
object Company{
    // thuộc tính member là danh sách tv công ty
    val members: MutableList<TeamMember> = mutableListOf()
    //thêm tv
    fun addMember(member: TeamMember){
        println("Thêm thành viên: ${member.name} vào công ty")
        members.add(member)
    }

    //hàm trả về thể hiện của công ty
    fun createDefaultCompany(): Company{
        val company = Company
        //Thêm tv mặc điịnh
        val developer1 = Developer("Tiến Hưng", 23, "DEV01", 1000.0, "kotlin")
        val manager1 = Manager("Tình", 35, "QL01", 5000.0)
        company.addMember(developer1)
        company.addMember(manager1)
        return company
    }
}



fun main(){
    //khởi tạo cty
    val myCompany = Company.createDefaultCompany()
    //Sử dug các đối tượng đã tạo
    val hung = myCompany.members.first() as Developer
    val tinh = myCompany.members.last()  as Manager

    //giới thiệu
    hung.introduce()
    tinh.introduce()

    //giao việc
    val task1 = Task("T001", "Làm giao diện login")
    hung.assignTask(task1)

    // cập nhật cv
    val completedTask = task1.copy(isCompleted = true)
    println("Hoàn thành công việc '${completedTask.taskName}' : ${completedTask.isCompleted}")

    //mọi người làm việc
    hung.work()
    tinh.work()
}
