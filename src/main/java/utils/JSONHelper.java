package utils;

import dtos.PetDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;

public final class JSONHelper {
    private JSONHelper() { }
    public static String convertToJSON(final PetDto petDto) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(petDto);
    }

    public static PetDto convertToPetDto(final String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, PetDto.class);
    }

    public static PetDto[] convertToPetDtos(final String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, PetDto[].class);
    }
}
