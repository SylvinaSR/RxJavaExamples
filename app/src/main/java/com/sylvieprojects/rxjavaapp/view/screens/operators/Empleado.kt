package com.sylvieprojects.rxjavaapp.view.screens.operators

import java.util.Date

data class Empleado(
    val id: Int,
    val name: String,
    val job: String,
    val date: String,
    val salary: Double,
    val plusSalary: Double
)

object EmpleadoDummyData {

    val listEmpleados = listOf<Empleado>(
        Empleado(id = 1, name = "Héctor", job = "Unemployee", date = "08/08/2025", salary = 0.0, plusSalary = 0.0),
        Empleado(id = 2, name = "Gaby", job = "LRC", date = "08/08/2025", salary = 35000.0, plusSalary = 0.0),
        Empleado(id = 3, name = "Ivan", job = "Backend Dev", date = "08/08/2025", salary = 25000.0, plusSalary = 0.0),
        Empleado(id = 4, name = "Yasir", job = "Architect", date = "08/08/2025", salary = 25000.0, plusSalary = 0.0),
        Empleado(id = 5, name = "Sylvina", job = "Android dev", date = "08/08/2025", salary = 52000.0, plusSalary = 0.0)
    )

    val empleado = Empleado(
        id = 15,
        name = "Rosita",
        job = "Bebe",
        date = "12/09",
        salary = 100000.0,
        plusSalary = 100000.0
    )

}
