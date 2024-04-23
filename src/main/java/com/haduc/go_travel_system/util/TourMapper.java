package com.haduc.go_travel_system.util;

import com.haduc.go_travel_system.dto.response.*;
import com.haduc.go_travel_system.entity.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TourMapper {
    public static TourResponse toDto(Tour tour) {
        TourResponse response = new TourResponse();
        response.setTourId(tour.getTourId());
        response.setName(tour.getName());
        response.setAdultPrice(tour.getAdultPrice());
        response.setChildPrice(tour.getChildPrice());
        response.setBabyPrice(tour.getBabyPrice());
        response.setUnit(tour.getUnit());
        response.setDescription(tour.getDescription());
        response.setNumberOfDays(tour.getNumberOfDays());
        response.setNumberOfNights(tour.getNumberOfNights());
        response.setVehicle(tour.getVehicle());
        response.setDepartureLocation(tour.getDepartureLocation());
        response.setHotelStar(tour.getHotelStar());
        response.setNumberOfSeats(tour.getNumberOfSeats());
        response.setAvailableSeats(tour.getAvailableSeats());
        response.setStatus(tour.getStatus());
        response.setNote(tour.getNote());
        response.setTourType(tour.getTourType());
        List<TourImage> images = tour.getImages();
        if (images != null) {
            response.setImages(images.stream().map(image -> TourImageReponse.builder()
                    .id(image.getId())
                    .url(image.getUrl())
                    .build()).toList());
        }
        List<DepartureTime> departureTimes = tour.getDepartureTimes();
        if (departureTimes != null) {
            response.setDepartureTimes(departureTimes.stream().map(departureTime -> DepartureTimeResponse.builder()
                    .id(departureTime.getId())
                    .startDate(departureTime.getStartDate())
                    .build()).toList());
        }
        List<TourSchedule> schedules = tour.getSchedules();
        if (schedules != null) {
            List<TourScheduleResponse> scheduleResponses = schedules.stream().map(schedule -> {
                TourScheduleResponse scheduleResponse = new TourScheduleResponse();
                scheduleResponse.setId(schedule.getId());
                scheduleResponse.setTimeInDays(schedule.getTimeInDays());
                scheduleResponse.setTitle(schedule.getTitle());
                List<ScheduleDetailResponse> scheduleDetailResponses = schedule.getScheduleDetail().stream().map(scheduleDetail -> {
                    ScheduleDetailResponse scheduleDetailResponse = new ScheduleDetailResponse();
                    scheduleDetailResponse.setId(scheduleDetail.getId());
                    scheduleDetailResponse.setTimeLine(scheduleDetail.getTimeLine());
                    scheduleDetailResponse.setPlace(scheduleDetail.getPlace());
                    scheduleDetailResponse.setActivity(scheduleDetail.getActivity());
                    return scheduleDetailResponse;
                }).toList();
                scheduleResponse.setScheduleDetail(scheduleDetailResponses);
                if (schedule.getTask() != null) {
                    TaskResponse taskResponse = new TaskResponse();
                    taskResponse.setId(schedule.getTask().getId());
                    taskResponse.setName(schedule.getTask().getTaskType().getName());
                    taskResponse.setDescription(schedule.getTask().getDescription());
                    taskResponse.setCoin(schedule.getTask().getCoin());
                    taskResponse.setReward(schedule.getTask().getReward());
                    taskResponse.setDeadline(schedule.getTask().getDeadline());
                    taskResponse.setStatus(schedule.getTask().getStatus());
                    TaskType taskType = schedule.getTask().getTaskType();
                    if (taskType != null) {
                        taskType.setId(schedule.getTask().getTaskType().getId());
                        taskType.setName(schedule.getTask().getTaskType().getName());
                    }
                    taskResponse.setTaskType(taskType);
                    scheduleResponse.setTask(taskResponse);
                } else {
                    scheduleResponse.setTask(null);
                }
                return scheduleResponse;
            }).toList();
            response.setSchedules(scheduleResponses);
        }
        return response;
    }

}
