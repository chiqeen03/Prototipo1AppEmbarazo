package com.example.mnlgu.prototipo1appembarazo.DB

class AuxiliarDatabase() {

    var babyInfoDB: MutableMap<Int, String>
    var ejerciciosDB: MutableMap<String, String>
    var dietaHidratosDeCarbono: MutableMap<String, String>
    var dietaProteinas: MutableMap<String, String>
    var dietaGrasas: MutableMap<String, String>
    var dietaAEvitar: MutableMap<String, String>
    var sintomasDB: MutableMap<String, String>
    var micronutrimentosDB: MutableMap<String, String>

    init {
        babyInfoDB = HashMap()
        ejerciciosDB = HashMap()
        dietaHidratosDeCarbono = HashMap()
        dietaProteinas = HashMap()
        dietaGrasas = HashMap()
        dietaAEvitar = HashMap()
        sintomasDB = HashMap()
        micronutrimentosDB = HashMap()

        fillBabyInfoDB()
        fillExerciseInfoDB()
        fillHidratosDeCarbono()
        fillProteinas()
        fillGrasas()
        fillAEvitar()
        fillSintomas()
        fillMicronutrimentos()
    }

    fun fillBabyInfoDB(){
        babyInfoDB.put(4,
            "Longitud aproximada: 5 mm\n" +
                    "\n" +
                    "Todavía no tiene aspecto humano, pero se pueden distinguir las protuberancias que luego serán la cabeza y el cuerpo del bebé.\n")
        babyInfoDB.put(5,
            "Longitud aproximada: 9 mm\n" +
                    "\n" +
                    "Su cerebro ha aumentado de tamaño en un tercio desde la última etapa, y todavía es más grande que el tronco")
        babyInfoDB.put(6,
            "Longitud aproximada: 11 mm\n" +
                    "\n" +
                    "La primera parte de la cara se forma\n" +
                    "\n" +
                    "La parte del cerebro que es responsable de la regulación del corazón, la respiración y los movimientos musculares, comienza a desarrollarse.\n")
        babyInfoDB.put(7,
            "Longitud aproximada: 18 mm\n" +
                    "\n" +
                    "Su cabeza es más erecta y comienza a formarse el oído interno, lo que permitirá una sensación de equilibrio y posición del cuerpo\n" +
                    "\n" +
                    "Se forma el tabique intermedio de su corazón\n" +
                    "\n" +
                    "Sus piernas están ahora en la ubicación correcta\n" +
                    "\n" +
                    "Los músculos se desarrollan y se hacen más fuertes.\n")
        babyInfoDB.put(8,
            "Longitud aproximada: 24 mm\n" +
                    "\n" +
                    "El período crítico del desarrollo de su corazón termina, seguirá creciendo pero ya no tan rápido\n" +
                    "\n" +
                    "Sus pies se alargan y se vuelven más definidos.\n" +
                    "\n" +
                    "Comienza el desarrollo de los órganos sexuales del feto\n")
        babyInfoDB.put(9,
            "Longitud aproximada: 26 mm\n" +
                    "\n" +
                    " Su oído externo está completamente desarrollado\n" +
                    "\n" +
                    "Sus papilas gustativas comienzan a formarse en la superficie de la lengua\n" +
                    "\n" +
                    "Sus extremidades superiores e inferiores están bien formadas. Los dedos se alargan y los dedos de los pies ya no están unidos y todos los dígitos están separados y son distintos.\n")
        babyInfoDB.put(10,
            "Longitud aproximada: 42 mm\n" +
                    "\n" +
                    "La estructura cerebral básica del feto está completa y ahora la masa cerebral aumenta rápidamente\n" +
                    "\n" +
                    "Su cara ya tiene apariencia humana\n" +
                    "\n" +
                    "Sus cuerdas vocales se forman en la laringe y el feto puede hacer sonidos\n" +
                    "\n" +
                    "Su piel es muy sensible\n")
        babyInfoDB.put(12,
            "Longitud aproximada: 61 mm\n" +
                    "Peso aproximado: 14 g\n" +
                    "\n" +
                    "El latido de su corazón puede ser detectado\n" +
                    "\n" +
                    "Sus pulmones se desarrollan más a medida que el feto inhala y exhala el líquido amniótico\n" +
                    "\n" +
                    "Los órganos sexuales que distinguen a la mujer o al hombre ahora son claramente visibles.\n" +
                    "\n" +
                    "Sus músculos funcionan más suavemente y el feto es más flexible y tiene movimientos avanzados de cabeza, boca y labios, brazos, muñecas, manos, piernas, pies y dedos de los pies.\n")
        babyInfoDB.put(14,
            "Longitud aproximada: 90 mm\n" +
                    "Peso aproximado: 25 g\n" +
                    "\n" +
                    "Sus extremidades están bien desarrolladas y más definidas con uñas de los pies que comienzan a crecer\n" +
                    "\n" +
                    "Es más recto y casi erecto a medida que los músculos y huesos se fortalecen\n")
        babyInfoDB.put(16,
            "Longitud aproximada: 111 cm\n" +
                    "Peso aproximado: 80 g\n" +
                    "\n" +
                    "Sus yemas de los dedos de manos y pies desarrollan los remolinos y pliegues únicos de las huellas dactilares, así como las huellas de los dedos\n" +
                    "\n" +
                    "Su circulación es completamente funcional\n")
        babyInfoDB.put(18,
            "Longitud aproximada: 14 cm\n" +
                    "Peso aproximado: 150 g\n" +
                    "\n" +
                    "Tiene fases de sueño y vigilia y puede preferir una posición favorita para dormir.\n" +
                    "\n" +
                    "Sus cejas comienzan a formarse\n" +
                    "\n" +
                    "El útero y ovarios de los fetos femeninos también están completamente formados\n")
        babyInfoDB.put(20,
            "Longitud aproximada: 16 cm\n" +
                    "Peso aproximado: 260 g\n" +
                    "\n" +
                    "El cabello de lanugo cubre completamente el cuerpo, aunque es más concentrado alrededor de la cabeza, el cuello y la cara.\n" +
                    "\n" +
                    "El latido del corazón se hace más fuerte\n" +
                    "\n" +
                    "Comienzan a surgir los alvéolos que darán lugar a los dientes\n")
        babyInfoDB.put(22,
            "Longitud aproximada: 19 cm\n" +
                    "Peso aproximado: 350 g\n" +
                    "\n" +
                    "Sus huesos de la oreja se endurecen, haciendo posible la conducción de sonido. Ya reconoce los sonidos maternos como la respiración, los latidos del corazón, la voz y la digestión.\n" +
                    "\n" +
                    "Sus huesos, músculos y órganos continúan desarrollandose\n")
        babyInfoDB.put(24,
            "Longitud aproximada: 21 cm\n" +
                    "Peso aproximado: 540 g\n" +
                    "\n" +
                    "Las ondas cerebrales del feto comienzan a activar los sistemas auditivos y visuales, tanto la boca como los labios muestran una mayor sensibilidad. \n" +
                    "\n" +
                    "Sus ojos responden a la luz, mientras que los oídos responden a sonidos que se originan fuera del útero. Sus fosas nasales comienzan a abrirse.")
        babyInfoDB.put(26,
            "Longitud aproximada: 23 cm\n" +
                    "Peso aproximado: 910 g\n" +
                    "\n" +
                    "Sus ojos están parcialmente abiertos y las pestañas están presentes.\n" +
                    "\n" +
                    "Sus pulmones están desarrollados y listos para para poder respirar\n")
        babyInfoDB.put(28,
            "Longitud aproximada: 25 cm\n" +
                    "Peso aproximado: 1.1 kg\n" +
                    "\n" +
                    "El cabello comienza a estar presente en la cabeza.\n" +
                    "\n" +
                    "Los golpes rítmicos y su temperatura corporal ahora son controlados por el cerebro\n")
        babyInfoDB.put(30,
            "Longitud aproximada: 27 cm\n" +
                    "Peso aproximado: 1.35 kg\n" +
                    "\n" +
                    "El rápido crecimiento del cerebro continúa y el tamaño de la cabeza aumenta a medida que el cerebro en crecimiento empuja el cráneo hacia afuera, va tomando forma.\n")
        babyInfoDB.put(32,
            "Longitud aproximada: 29 cm\n" +
                    "Peso aproximado: 1.8 kg\n" +
                    "\n" +
                    "Sus ojos se abren durante los tiempos de alerta y se cierran durante el sueño. El color de sus ojos suele ser azul, independientemente del color permanente, ya que la pigmentación no está completamente desarrollada\n" +
                    "\n" +
                    "Comienza a desarrollar su propio sistema inmunológico.\n" +
                    "\n" +
                    "Las uñas se extienden sobre las puntas de los dedos y el feto puede rascarse.\n")
        babyInfoDB.put(34,
            "Longitud aproximada: 32 cm\n" +
                    "Peso aproximado: 2.28 kg\n" +
                    "\n" +
                    "Su sistema gastrointestinal es muy inmaduro y no madurará hasta tres o cuatro años después del nacimiento.\n" +
                    "\n" +
                    "Sus extremidades comienzan a formar hoyuelos en los codos y rodillas, y las arrugas se forman alrededor de las muñecas y el cuello.\n")
        babyInfoDB.put(36,
            "Longitud aproximada: 34 cm\n" +
                    "Peso aproximado: 2.75 kg\n" +
                    "\n" +
                    "Hace reservas de grasa en su cuerpo para mantener su temperatura corporal, para este momento sus huesos son flexibles y le permiten adoptar diferentes posiciones\n")
        babyInfoDB.put(38,
            "Longitud aproximada: 35 cm\n" +
                    "Peso aproximado: 3.1 kg\n" +
                    "\n" +
                    "Su cráneo no se encuentra totalmente sólido para que pueda adaptarse al canal vaginal al momento del parto, posteriormente vuelve a su forma redonda y se vuelve sólido\n")
        babyInfoDB.put(40,
            "Longitud del neonato: aproximadamente 48 cm\n" +
                    "Peso aproximado: 3.4 kg\n" +
                    "\n" +
                    "En el momento del nacimiento, el bebé tiene un total de 300 huesos. Algunos huesos se fusionarán más tarde, para que terminen siendo 206 huesos.\n" +
                    "\n" +
                    "Ya presenta reflejos no aprendidos, pero esenciales para la supervivencia\n")
    }

    fun fillExerciseInfoDB(){
        ejerciciosDB.put("descripcion", "Realizar ejercicio durante tu embarazo mejorará tu condición física, tu estado de ánimo, controlará tu ganancia de peso, mejora la digestión y el estreñimiento y evita que desarrolles diabetes gestacional. \n" +
                "\n" +
                "*Contrario a lo que se cree, el ejercicio durante el embarazo no modifica la talla o el peso del bebé, ni lo pone en peligro. Sin embargo, debes consultar a tu doctor antes de realizar ejercicio ya que algunos padecimientos como enfermedad cardiaca, pre-eclampsia, riesgo de parto prematuro, entre otras, contraindican el ejercicio en el embarazo.\n")
        ejerciciosDB.put("aerobicos", "Debes realizar entre 150- 300 minutos de ejercicio moderado aeróbico a la semana. \n" +
                "*Ten cuidado con los ejercicio que comprometen las articulaciones como correr, fútbol y tenis.\n")
        ejerciciosDB.put("fuerza", "Entrena fuerza 3 veces a la semana por 30 minutos. Puedes utilizar pesas pero realiza muchas repeticiones con bajo peso.")
        ejerciciosDB.put("consideraciones", "Ten cuidado con los deportes que aumentan el riesgo de caídas y deportes extremos como paracaidismo, surfear y deportes de contacto como box, taekwondo y karate. \n" +
                "El buceo y la equitación son clasificados como deportes prohibidos en el embarazo.\n")
    }

    fun fillHidratosDeCarbono(){
        dietaHidratosDeCarbono.put("fibra", "Se recomienda ingerir fibra diario de las leguminosas, cereales integrales, verduras y frutas. La fibra previene el estreñimiento, la obesidad, diabetes,disminuye los niveles de colesterol LDL “malo” y produce saciedad.\n")
        dietaHidratosDeCarbono.put("frutas y verduras", "Se recomienda que comas por lo menos 5 frutas y verduras de diferentes colores para que obtengas todos los antioxidantes necesarios.\n" +
                "\n" +
                "\t-Frutas y verduras rojas: Protegen contra enfermedades cardiovasculares, cáncer y envejecimiento.\n" +
                "\t-Frutas y verduras verdes: Protegen la visión, contienen vitamina C, vitamina K y ácido fólico. (dar click y lo lleva a la parte de ácido fólico en micronutrimentos a cuidar). \n" +
                "\t-Frutas y verduras naranja: Contienen vitamina A (dar click y lo lleva a la parte de vitamina A en micronutrimentos a cuidar), ayudan a conservar la visión, piel sana y mejoran el sistema inmunitario. \n" +
                "\t-Frutas y verduras moradas: Disminuyen el riesgo de cáncer, envejecimiento y preservan la memoria. \n" +
                "\t-Frutas y verduras blancas: Controlan los niveles de glucosa y lípidos en la sangre, son anticancerígenas y antisépticas. ")
        dietaHidratosDeCarbono.put("leguminosas", "La recomendación es ingerir 2 porciones al día de leguminosas como frijoles, garbanzo, lentejas, chícharos, habas, ejotes. Son ricas en fibra, proteína, vitamina B1 y B3 y magnesio.")
        dietaHidratosDeCarbono.put("azucares", "Debes de controlar la cantidad de azúcares añadidos que ingieres. La recomendación es de no más de  25 gramos al día, lo que se traduce a no más de 5 cucharaditas de azúcar diarias (ya que 1 cucharadita equivale a 5 gramos de azúcar). Los productos industrializados que más azúcar contienen son: jugos, refrescos, cereales de desayuno, pan dulce, cerveza, dulces, postres y pan y cereales blancos (no integrales).")
    }

    fun fillProteinas() {
        dietaProteinas.put("pescado", "Deberías de ingerir 240 gramos de pescado a la semana. Es una gran fuente de zinc, proteína y omega 3.")
        dietaProteinas.put("lacteos", "Se recomienda el consumo de 3 vasos de leche baja en grasa al día ya que aporta proteína y es rica en calcio y vitamina D.\n" +
                "El queso y el yogurt también aportan proteína, calcio y vitamina D pero suelen contener más grasas que la leche. \n")
        dietaProteinas.put("embutidos", "El consumo de embutidos como jamón, salchicha, chorizo fiambre, mortadela, pepperoni, jamón serrano debe de ser evitado. Hay evidencia de daño cardiovascular y cáncer al consumir estos productos. Si los consumes, procura que no sobrepase las 2 porciones a la semana.")
        dietaProteinas.put("otros", "Pollo, puerco, cabrito, calamar, camarón, huevo, jaiba, langosta, pavo, conejo.")
    }

    fun fillGrasas(){
        dietaGrasas.put("omega 3", "El omega 3 es una grasa poliinsaturada (“buena”) que aporta beneficios tanto a la mamá como al bebé. El omega 3 reduce la inflamación, reduce el riesgo de enfermedades cardiovasculares, es importante para el desarrollo neurológico, mejoran la visión y se ha visto que, los hijos de las madres que consumieron omega 3 en el embarazo, tienen niveles de inteligencia más elevados. Es por eso que te recomendamos que comas por lo menos, 2 veces a la semana pescado o mariscos. ")
        dietaGrasas.put("omega 6", "El omega 6 es una grasa poliinsaturada (“buena”) que reduce el riesgo de enfermedades cardiovasculares, disminuye los niveles de colesterol total y LDL (“malo”). Lo puedes consumir en aceites vegetales como el de cártamo, maíz, girasol, aguacate y soya.")
        dietaGrasas.put("saturadas", "Las grasas saturadas se encuentran principalmente en grasas animales sólidas o en alimentos industrializados. Tu dieta debe de ser baja en este tipo de grasas ya que incrementa los niveles de colesterol LDL (“malo”) y puede bloquear las arterias. Además, su consumo excesivo está relacionado con el desarrollo de obesidad. El helado, la mantequilla, la leche entera, consumir mucha carne de res, crema, aderezos y quesos son altos en este tipo de grasa.")
        dietaGrasas.put("trans", "Las grasas trans son utilizadas por la industria, por lo que no existen en los alimentos naturales. El proceso consiste en hacer un aceite líquido, sólido para disminuir gastos. Debes evitar ingerir alimentos con este tipo de grasas ya que aumentan el riesgo de desarrollar enfermedades cardiovasculares y cáncer.")
    }

    fun fillAEvitar(){
        dietaAEvitar.put("alcohol", "La ingestión de alcohol es teratogénico. Esto quiere decir, que puede causar un defecto de nacimiento al bebé. Los bebés desarrollan el síndrome alcohólico fetal cuando la madre bebe alcohol durante su embarazo y se caracteriza de:\n" +
                "\t-Retraso en el crecimiento \n" +
                "\t-Crecimiento pobre del cerebro del bebé\n" +
                "\t-Deformidades faciales y articulares\n")
        dietaAEvitar.put("cafe", "Se recomienda que el consumo diario de cafeína no exceda las 2 tazas diarias ya que puede elevar el riesgo de aborto y bajo peso del recién nacido.")
    }

    fun fillSintomas(){
        sintomasDB.put("estrenimiento", "Recuerda ingerir suficiente fibra a través del consumo de cereales integrales, frutas, verduras y leguminosas. La fibra facilita la defecación ya que aumenta los movimientos intestinales y hace las heces más voluminosas y menos consistentes. \n" +
                "\n" +
                "Tomar agua también ayuda a hacer las heces más voluminosas y mejora el tránsito intestinal.\n" +
                "\n" +
                "Por último, te recomendamos estar en movimiento. Hacer actividad física ayuda con los movimientos intestinales.\n")
        sintomasDB.put("acidez", "Es importante no dormir con el estómago vacío pero tampoco cenar de más. Sí la acidez es muy molesta, es recomendable dormir semi acostada.\n")
        sintomasDB.put("nauseas", "Es importante no dormir con el estómago vacío pero tampoco cenar de más. Sí la acidez es muy molesta, es recomendable dormir semi acostada.\n")
    }

    fun fillMicronutrimentos(){
        micronutrimentosDB.put("hierro", "Importante para el desarrollo neurológico del bebé, transporte de oxígeno y prevención de anemia por deficiencia de hierro. Puedes consumirlo a través de la carne roja, sin embargo, la cantidad que requieres es muy elevada debido a tu embarazo por lo que la comida no es suficiente. Recuerda tomar tu suplemento.")
        micronutrimentosDB.put("acido folico", "Es esencial para el óptimo desarrollo cerebral de tu bebé y la síntesis de ADN. Puedes obtenerlo de las verduras de hoja verde. Pero el requerimiento es muy elevado debido al embarazo, por lo que es de suma importancia que tomes el suplemento diario. ")
        micronutrimentosDB.put("vitamina a", "La vitamina a se encuentra en todas las frutas y verduras de color naranja y amarillo y en alimentos de origen animal. Esta vitamina juega un papel importante en el desarrollo de visión nocturna de tu bebé.")
        micronutrimentosDB.put("calcio y vitamina d", "El calcio es de suma importancia para formar los huesos  del bebé. La función de la vitamina D es asegurar que el calcio se absorba para que cumpla su función. Además, se ha encontrado que personas con niveles óptimos de calcio, disminuye el riesgo de tensión arterial alta. Ambos los puedes encontrar en altas concentraciones en la leche baja en grasa. ")
        micronutrimentosDB.put("zinc", "El zinc es importante para sintetizar los músculos y ADN de tu bebé. Al ingerir pescado, te aseguras de que tu bebé está recibiendo los micronutrimentos necesarios para su desarrollo.")
    }

}