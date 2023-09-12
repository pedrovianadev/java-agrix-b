package com.betrybe.agrix.controller.dto;

/**
 * Creates the response data for the creation of crops.
 *
 * @param id id of the created crop
 * @param name name of the crop created
 * @param plantedArea planted area of the crop created
 * @param farmId id of the farm where the crop was created
 */
public record CropResponseDto(Long id, String name, Double plantedArea, Long farmId) {
}