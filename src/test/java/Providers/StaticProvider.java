package Providers;

import enums.Statuses;
import models.Pet;
import org.testng.annotations.DataProvider;

public class StaticProvider {
    @DataProvider(name = "possible-ids-to-create-pet")
    public static Object[][] possibleIdsToCreatePet(){
        return new Object[][] {
                { new Pet().withSimpleFields(), " id is a positive integer"},
                { new Pet().withSimpleFields().withNegativeId(), "id is a negative integer"},
                { new Pet().withSimpleFields().withZeroId(), "id equals zero"},
                { new Pet().withSimpleFields().withId(null), "id equals null"},
                { new Pet().withSimpleFields().withLongId(), "id is long"},
        };
    }

    @DataProvider(name = "possible-names-to-create-pet")
    public static Object[][] possibleINamesToCreatePet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withLongName(), " with long name"},
                { new Pet().withSimpleFields().withName(null), " with null name"},
                { new Pet().withSimpleFields().withName(""), " with empty name"},
                { new Pet().withSimpleFields().withNameWithSpecialCharacters(), " with name with special characters"},
        };
    }

    @DataProvider(name = "possible-statuses-to-create-pet")
    public static Object[][] possibleStatusesPet(){
        return new Object[][] {
                { new Pet().withSimpleFields().withStatus(Statuses.Available), "with status Available"},
                { new Pet().withSimpleFields().withStatus(Statuses.Sold), "with status Sold"},
                { new Pet().withSimpleFields().withStatus(Statuses.Pending), "with status Pending"},
                { new Pet().withSimpleFields().withStatus(Statuses.AvailableLowerCase),
                        "with status Available written in lower case"},
                { new Pet().withSimpleFields().withStatus(Statuses.NotExisted), "with not existed status"},
                { new Pet().withSimpleFields().withStatus(Statuses.Null), "with null status"},
                { new Pet().withSimpleFields().withStatus(Statuses.Empty), "with empty status"},
                { new Pet().withSimpleFields().withStatus(Statuses.SpecialCharacters),
                        "with special characters in status"},
        };
    }
}
