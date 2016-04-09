package ua.ck.solo.aidkit.bootstrap

import ua.ck.solo.aidkit.Drug
import ua.ck.solo.aidkit.Role
import ua.ck.solo.aidkit.Structure
import ua.ck.solo.aidkit.Tag
import ua.ck.solo.aidkit.User
import ua.ck.solo.aidkit.UserRole

/**
 * Created by igor on 4/9/16.
 */
class BootStrapData {

    static public createData() {

        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new User(username: 'gisr02', password: 'test', email: 'solo.ck.ua@gmail.com')
        adminUser.save(flush: true)

        UserRole.create adminUser, adminRole, true

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 1

        Structure liquid = new Structure(title: 'жидкость').save(failOnError: true)
        Structure oil = new Structure(title: 'олия').save(failOnError: true)
        Structure grass = new Structure(title: 'трава').save(failOnError: true)
        Structure ointment = new Structure(title: 'мазь').save(failOnError: true)
        Structure pill = new Structure(title: 'таблетки').save(failOnError: true)
        Structure spray = new Structure(title: 'спрей').save(failOnError: true)

        Tag tagGrass = new Tag(name: 'Травы', user: adminUser).save(failOnError: true)
        Tag tagCold = new Tag(name: 'Насморк', user: adminUser).save(failOnError: true)

        def instructionsValidol = """
Валидол – успокаивающее средство.
    Фармакологическое действие
Действующее вещество Валидола - раствор ментола в ментил изовалерате.
Препарат оказывает успокаивающе и рефлекторное сосудорасширяющее действие, что вызвано раздражением нервных окончаний.
Также препарат, воздействуя на рецепторы слизистой, обеспечивает выработку ряда веществ, принимающих в участии в регуляции проницаемости сосудов.
При рассасывании таблетки Валидола под языком эффект наступает через 5мин.
    Форма выпуска
Выпускают Валидол таблетки, капсулы и капли для сублингвального применения (подъязычные).
    Показания к применению Валидола
По инструкции Валидол назначают при стенокардии, кардиалгии, неврозе, головной боли из-за применения нитратов, истерии.
Эффективно применение Валидола при синдроме укачивания, для устранения тошноты, рвоты при воздушной и морской болезни.
    Инструкция по применению Валидола
Таблетки Валидол принимают сублингвально (под язык) по 1й таблетке 2-3р/день. В зависимости от эффективности лечебного курса определяется его продолжительность.
Если после принятия таблетки в течение 5-10мин. не наблюдается должный терапевтический эффект, желательно применить другие средства.
Капли принимают по 3-6кап. за один прием. Валидол капают на сахар, который держат во рту до растворения.
По инструкции Валидола его суточная доза должна быть не больше 9-24 капель, а кратность приемов – 3-4/сутки.
Доза может быть увеличена, если по медицинским показаниям это необходимо. Применение Валидола в капсулах проводится по схеме применения таблеток.
    Побочные действия
Применение Валидола в редких случаях может вызвать слезоточение, легкую тошноту, головокружение.
Устранение этих симптомов не требует проведения процедур экстренной помощи, обычно они проходят самостоятельно.
    Противопоказания к применению Валидола
Средство не назначают при индивидуальной непереносимости его составляющих, при выраженной гипотонии, остром инфаркте миокарда.
С осторожностью назначают Валидол при беременности, в период кормления грудью.
Валидол при беременности может быть назначен только по особым медицинским показателям.
Если принимать Валидол по инструкции, в разрешенных нормах, он не вызывает проблем со скоростью реакции,
а значит возможно управление транспортом и другими механизмами во время прохождения терапии. Возможность применения Валидола детьми не изучалась.
В составе Валидола нет сахара, поэтому его разрешено принимать больным диабетом (без применения сахара как вспомогательного вещества).
"""

        Drug peroxide = new Drug(
                title: 'Перекис водню розчин',
                titleEn: 'Hydrogen peroxide solution',
                structure: liquid,
                instructions: '<html> <h1>Инструкция Перекис водню розчин</h1> </html>',
                endingDate: new Date('2016/12/30'),
                comment: 'комментарий к перекиси',
                user: adminUser
        ).save(failOnError: true)

        Drug validol = new Drug(
                title: 'Валідол',
                titleEn: 'Validol',
                structure: pill,
                instructions: instructionsValidol,
                endingDate: new Date('2016/07/01'),
                comment: 'комментарий к Валидолу',
                user: adminUser
        ).save(failOnError: true)

        Drug nasivin = new Drug(
                title: 'Називін',
                titleEn: 'Nasivin',
                description: 'Спрей назальний 0,025%',
                structure: spray,
                endingDate: new Date('2017/10/01'),
                user: adminUser
        ).save(failOnError: true)

        nasivin.addToTags(tagCold)

        Drug aquaMaris = new Drug(
                title: 'АКВА МАРІС',
                description: 'Спрей назальний',
                structure: spray,
                endingDate: new Date('2016/07/01'),
                comment: 'Ізотонічний стерильний розчин морської води з натуральними солями та мікроелементами',
                user: adminUser
        ).save(failOnError: true)

        aquaMaris.addToTags(tagCold)

        Drug bepanthen = new Drug(
                title: 'Бепантен',
                titleEn: 'Bepanthen',
                description: 'Мазь 5% для догляду за немовлятами',
                structure: ointment,
                endingDate: new Date('2017/03/01'),
                user: adminUser
        ).save(failOnError: true)

        Drug menovasin = new Drug(
                title: 'Меновазин',
                titleEn: 'Menovasinum',
                description: 'Розчин нашкірний',
                structure: liquid,
                endingDate: new Date('2015/06/01'),
                user: adminUser
        ).save(failOnError: true)

        Drug uzara = new Drug(
                title: 'Узара',
                description: 'Рослинний лікарський препарат для лікування діареї',
                structure: grass,
                endingDate: new Date('2016/01/01'),
                user: adminUser
        ).save(failOnError: true)

        Drug zelenka = new Drug(
                title: 'Брильянтовий зелений',
                description: 'Зеленка',
                structure: liquid,
                endingDate: new Date('2017/06/17'),
                user: adminUser
        ).save(failOnError: true)

        Drug kamforna = new Drug(
                title: 'Камфорна олія',
                description: 'Протиревматичний засіб',
                structure: oil,
                endingDate: new Date('2017/08/01'),
                user: adminUser
        ).save(failOnError: true)

        Drug chlorophyllipt = new Drug(
                title: 'Хлорофилипт',
                titleEn: 'Chlorophyllipt',
                description: 'Раствор спиртовой 10мг/мл',
                structure: liquid,
                endingDate: new Date('2016/01/01'),
                user: adminUser
        ).save(failOnError: true)

        Drug cortex = new Drug(
                title: 'Дуба кора',
                titleEn: 'Quercus cortex',
                structure: grass,
                endingDate: new Date('2019/01/01'),
                user: adminUser
        ).save(failOnError: true)

        cortex.addToTags(tagGrass)

        Drug nifuroxazide = new Drug(
                title: 'Ніфуроксазид ріхтер',
                titleEn: 'Nifuroxazide',
                description: 'Суспензія оральна',
                structure: liquid,
                endingDate: new Date('2015/06/01'),
                user: adminUser
        ).save(failOnError: true)
    }
}
