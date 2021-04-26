package Utils;

import DTOs.PetDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;

public class JSONHelper {
    public static String ConvertToJSON(PetDto petDto) throws IOException
    {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(petDto);
    }

    public static PetDto ConvertToPetDto(String json) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json , PetDto.class);
    }

    public static PetDto[] ConvertToPetDtos(String json) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json , PetDto[].class);
    }
}
