import org.junit.jupiter.api.Test;
/*В таком виде название пакетов подставила автоматически IDEA при нажатии не Alt+Enter
*если пишу так import org.junit.jupiter.api.test
* то IDEA выдает ошибку
* cannot resolve symbol 'test'
*
* Поясните ваше замечание
*/
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParcelTest {
    @Test
    void testCalculateDeliveryCost() {
        // Создаем посылки разных типов
        StandardParcel standardParcel = new StandardParcel("Стандартная посылка", 10, "Адрес", 1);
        FragileParcel fragileParcel = new FragileParcel("Хрупкая посылка", 5, "Адрес", 1);
        PerishableParcel perishableParcel = new PerishableParcel("Скоропортящаяся посылка", 7, "Адрес", 1, 3);

        // Проверяем вычисление стоимости доставки
        assertEquals(20, standardParcel.calculateDeliveryCost());
        assertEquals(20, fragileParcel.calculateDeliveryCost());
        assertEquals(21, perishableParcel.calculateDeliveryCost());
    }

    @Test
    void testIsExpired() {
        // Создаем скоропортящуюся посылку
        PerishableParcel perishableParcel = new PerishableParcel("Скоропортящаяся посылка", 7, "Адрес", 1, 3);

        // Проверяем метод isExpired
        assertFalse(perishableParcel.isExpired(2)); // Не испортилась
        assertTrue(perishableParcel.isExpired(5));  // Испортилась
    }

    @Test
    void testAddParcelToBox() {
        // Создаем коробку с максимальным весом 50
        ParcelBox<Parcel> parcelBox = new ParcelBox<>(50);

        // Создаем несколько посылок
        StandardParcel standardParcel1 = new StandardParcel("Посылка 1", 10, "Адрес", 1);
        StandardParcel standardParcel2 = new StandardParcel("Посылка 2", 20, "Адрес", 1);
        StandardParcel standardParcel3 = new StandardParcel("Посылка 3", 30, "Адрес", 1);

        // Добавляем посылки в коробку
        parcelBox.addParcel(standardParcel1);
        parcelBox.addParcel(standardParcel2);
        parcelBox.addParcel(standardParcel3); // Должна быть отклонена из-за превышения веса

        // Проверяем содержимое коробки
        assertEquals(2, parcelBox.getAllParcels().size());
    }
}