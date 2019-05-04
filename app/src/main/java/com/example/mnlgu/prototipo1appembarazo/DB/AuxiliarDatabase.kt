package com.example.mnlgu.prototipo1appembarazo.DB

class AuxiliarDatabase() {

    val babyInfoDB: MutableMap<Int, String> = HashMap()

    init {
        fillBabyInfoDB()
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

}