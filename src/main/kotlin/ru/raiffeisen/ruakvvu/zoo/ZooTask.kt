package ru.raiffeisen.ruakvvu.zoo

import java.time.LocalDate
import java.time.LocalTime
import java.util.LinkedList

//еда (ид, наименование, вес?, калории?)
data class Food(val id: Int, val name: String, val weight: Int, val calories: Int)
//докторы (ид, ФИО, образование?, специализация? (null - терапевт))
data class Doctor(val id: Int, val fullName: String, val education: String?, val specialization: String?)
//вольеры (ид, номер, начало_уборки_час, конец_уборки_час)
data class Enclosure(val id: Int, val number: String,  val startCleaning: LocalTime, val endCleaning: LocalTime)
//звери (ид, кличка?, вид, вес?)
data class Beast(val id: Int, val name: String?, val type: String, val weight: Int?)
//размещение (вольер_ид, зверь_ид, дата_начала, дата_окончания)
data class Beast2Enclosure(val enclosureId: Int, val beastId: Int, val startDate: LocalDate, val endDate: LocalDate)
//питание (зверь_ид, еда_ид, количество, дата)
data class Nutrition(val beastId: Int, val foodId: Int, val count: Int, val date: LocalDate)
//лечение (доктор_ид? (null - самолечение), зверь_ид, дата)
data class Treatment(val beastId: Int, val date: LocalDate, val docId: Int?)

data class Zoo(
    val foods: List<Food>,
    val doctors: List<Doctor>,
    val enclosures: List<Enclosure>,
    val beasts: List<Beast>,
    val beast2enclosures: List<Beast2Enclosure>,
    val nutrition: List<Nutrition>,
    val treatments: List<Treatment>
)

//Есть ли в зоопарке медведь по кличке "Бугор", весом не менее 400 кг, занимавшийся самолечением в один из
//дней периода 20.05.2023 .. 29.06.2023
fun findBearByName(zoo: Zoo, name: String, weight: Int, treatmentStart: LocalDate, treatmentEnd:LocalDate) =
    zoo.treatments.find {
        it.beastId == zoo.beasts.find { beast ->
            beast.type == "BEAR" && beast.name == name && beast.weight == weight
        }?.id && (it.date.isAfter(treatmentStart) && it.date.isBefore(treatmentEnd))
    }
//Найдите суммарный вес еды, съеденной зверями из вольера номер 6 за 20.05.2023. В случае, если вес еды не указан,
//взять вес равный 1.0кг. Ответ дать в типе Double
fun getFoodEatenWeight(zoo: Zoo, enclosureNumber: String, byDate: LocalDate): Double =
    zoo.nutrition.filter { nutrition ->
        zoo.beasts.filter { beast ->
            zoo.beast2enclosures.filter { beast2enclosure ->
                beast2enclosure.enclosureId == zoo.enclosures.find { enclosure ->
                    enclosure.number == enclosureNumber
                }?.id
            }.map { b2e -> b2e.beastId }.contains(beast.id)
        }.map { b -> b.id }.contains(nutrition.beastId) && nutrition.date === byDate
    }.associate {
            n -> n.foodId to n.count
    }.entries.map { nutritionMap ->
        (zoo.foods.find { food ->
            food.id == nutritionMap.key
        }?.weight ?: 1) * nutritionMap.value
    }.fold(0) {
            acc, next -> acc + next
    }.toDouble()

//Сопоставьте каждому зверю, больному ожирением (вес больше 120кг и оно при этом не медведь), запись на прием к
//доктору профильной специальности 'ожиролог', имеющему образование в 'ОмГМУ' на 20.05.2023. Если врачи уже заняты
//в данный день, или врачей не хватает, то сопоставьте null. Ответ дать в виде Map<Animal, Doctor?>
fun matchDoctorsForObeseBeast(zoo: Zoo,
                              docSpecialization: String,
                              docEducation: String,
                              forDate: LocalDate): Map<Beast, Doctor?> {
    val availableDoctors = LinkedList(zoo.doctors.filter { doctor ->
        zoo.treatments.find { treatment ->
            treatment.date == forDate
        }?.docId != doctor.id && doctor.specialization == docSpecialization && doctor.education == docEducation
    })

    val result = zoo.beasts.filter { beast ->
        beast.type != "BEAR" && (beast.weight ?: 0) > 120
    }.associateWith {
        availableDoctors.poll()
    }

    return result;
}
