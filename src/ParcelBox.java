import java.util.ArrayList;
import java.util.List;

class ParcelBox<T extends Parcel> {
    private List<T> parcels;
    private int maxWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        parcels = new ArrayList<>();
    }

    void addParcel(T parcel) {
        if (getTotalWeight() + parcel.getWeight() <= maxWeight) {
            parcels.add(parcel);
            System.out.println("Посылка добавлена в коробку.");
        } else {
            System.out.println("ПРЕДУПРЕЖДЕНИЕ: Превышен максимальный вес коробки! Посылка не добавлена.");
        }
    }

    List<T> getAllParcels() {
        return parcels;
    }
    void updateLocation(String newLocation) {
        for (Parcel parcel : parcels) {
            if (parcel instanceof Trackable) {
                ((Trackable) parcel).reportStatus(newLocation);
            }
        }
    }

    int getTotalWeight() {
        int totalWeight = 0;
        for (Parcel parcel : parcels) {
            totalWeight += parcel.getWeight();
        }
        return totalWeight;
    }
}