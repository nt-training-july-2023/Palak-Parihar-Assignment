package com.grievance.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.grievance.entity.Status;
import com.grievance.entity.TicketType;

@SpringBootTest
public class TicketDtoTests {

  @Test
  void test_ticketInDto_equals() {
    TicketInDto ticketInDto = new TicketInDto();
    ticketInDto.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto.setDescription("Description");
    ticketInDto.setTicketType(TicketType.GRIEVANCE);
    ticketInDto.setTitle("Title");

    TicketInDto ticketInDto2 = new TicketInDto();
    ticketInDto2.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto2.setDescription("Description");
    ticketInDto2.setTicketType(TicketType.GRIEVANCE);
    ticketInDto2.setTitle("Title");

    TicketInDto ticketInDto3 = new TicketInDto();
    ticketInDto3.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto3.setDescription("Description");
    ticketInDto3.setTicketType(TicketType.FEEDBACK);
    ticketInDto3.setTitle("Title");

    assertThat(ticketInDto.equals(ticketInDto2));

    assertThat(!ticketInDto.equals(ticketInDto3));

  }

  @Test
  void test_ticketInDto_hashcode() {
    TicketInDto ticketInDto = new TicketInDto();
    ticketInDto.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto.setDescription("Description");
    ticketInDto.setTicketType(TicketType.GRIEVANCE);
    ticketInDto.setTitle("Title");

    TicketInDto ticketInDto2 = new TicketInDto();
    ticketInDto2.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto2.setDescription("Description");
    ticketInDto2.setTicketType(TicketType.GRIEVANCE);
    ticketInDto2.setTitle("Title");

    TicketInDto ticketInDto3 = new TicketInDto();
    ticketInDto3.setDepartment(new DepartmentInDto(101, "HR"));
    ticketInDto3.setDescription("Description");
    ticketInDto3.setTicketType(TicketType.FEEDBACK);
    ticketInDto3.setTitle("Title");

    assertEquals(ticketInDto.hashCode(), ticketInDto2.hashCode());

    assertNotEquals(ticketInDto.hashCode(), ticketInDto3.hashCode());
  }

  @Test
  void test_ticketOutDto_equals() {
    TicketOutDto ticketOutDto = new TicketOutDto();
    ticketOutDto.setDepartment("HR");
    ticketOutDto.setDescription("Description");
    ticketOutDto.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto.setStatus(Status.BEING_ADDRESSED);
    ticketOutDto.setTicketId(101);

    TicketOutDto ticketOutDto2 = new TicketOutDto();
    ticketOutDto2.setDepartment("HR");
    ticketOutDto2.setDescription("Description");
    ticketOutDto2.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto2.setStatus(Status.BEING_ADDRESSED);
    ticketOutDto2.setTicketId(101);

    TicketOutDto ticketOutDto3 = new TicketOutDto();
    ticketOutDto3.setDepartment("HR");
    ticketOutDto3.setDescription("Description");
    ticketOutDto3.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto3.setStatus(Status.OPEN);
    ticketOutDto3.setTicketId(101);

    assertThat(ticketOutDto.equals(ticketOutDto2));

    assertThat(!ticketOutDto.equals(ticketOutDto3));
  }
  
  @Test
  void test_ticketOutDto_hashcode() {
    TicketOutDto ticketOutDto = new TicketOutDto();
    ticketOutDto.setDepartment("HR");
    ticketOutDto.setDescription("Description");
    ticketOutDto.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto.setStatus(Status.BEING_ADDRESSED);
    ticketOutDto.setTicketId(101);

    TicketOutDto ticketOutDto2 = new TicketOutDto();
    ticketOutDto2.setDepartment("HR");
    ticketOutDto2.setDescription("Description");
    ticketOutDto2.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto2.setStatus(Status.BEING_ADDRESSED);
    ticketOutDto2.setTicketId(101);

    TicketOutDto ticketOutDto3 = new TicketOutDto();
    ticketOutDto3.setDepartment("HR");
    ticketOutDto3.setDescription("Description");
    ticketOutDto3.setEmployee("ayushi@nucleusteq,com");
    ticketOutDto3.setStatus(Status.OPEN);
    ticketOutDto3.setTicketId(101);
    
    assertEquals(ticketOutDto.hashCode(), ticketOutDto2.hashCode());
    assertNotEquals(ticketOutDto.hashCode(), ticketOutDto3.hashCode());
  }
  
  @Test
  void test_ticketUpdate_equals() {
    TicketUpdateDto ticketUpdateDto = new TicketUpdateDto();
    ticketUpdateDto.setDescription("Update");
    ticketUpdateDto.setStatus(Status.RESOLVED);
    
    TicketUpdateDto ticketUpdateDto2 = new TicketUpdateDto();
    ticketUpdateDto2.setDescription("Update");
    ticketUpdateDto2.setStatus(Status.OPEN);
    
    TicketUpdateDto ticketUpdateDto3 = new TicketUpdateDto();
    ticketUpdateDto3.setDescription("Update");
    ticketUpdateDto3.setStatus(Status.RESOLVED);
    
    assertThat(!ticketUpdateDto.equals(ticketUpdateDto2));
    assertThat(ticketUpdateDto.equals(ticketUpdateDto3));
  }
  
  @Test
  void test_ticketUpdate_hashcode() {
    TicketUpdateDto ticketUpdateDto = new TicketUpdateDto();
    ticketUpdateDto.setDescription("Update");
    ticketUpdateDto.setStatus(Status.RESOLVED);
    
    TicketUpdateDto ticketUpdateDto2 = new TicketUpdateDto();
    ticketUpdateDto2.setDescription("Update");
    ticketUpdateDto2.setStatus(Status.OPEN);
    
    TicketUpdateDto ticketUpdateDto3 = new TicketUpdateDto();
    ticketUpdateDto3.setDescription("Update");
    ticketUpdateDto3.setStatus(Status.RESOLVED);
    
    assertNotEquals(ticketUpdateDto.hashCode(), ticketUpdateDto2.hashCode());
    
    assertEquals(ticketUpdateDto.hashCode(), ticketUpdateDto3.hashCode());
  }
  
  @Test
  void test_ticketWOComment_hashcode() {
    TicketOutWOComment ticketOutWOComment = new TicketOutWOComment();
    ticketOutWOComment.setDepartment("HR");
    ticketOutWOComment.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment.setStatus(Status.BEING_ADDRESSED);
    ticketOutWOComment.setTicketId(101);
    
    TicketOutWOComment ticketOutWOComment2 = new TicketOutWOComment();
    ticketOutWOComment2.setDepartment("HR");
    ticketOutWOComment2.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment2.setStatus(Status.OPEN);
    ticketOutWOComment2.setTicketId(101);
    
    TicketOutWOComment ticketOutWOComment3 = new TicketOutWOComment();
    ticketOutWOComment3.setDepartment("HR");
    ticketOutWOComment3.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment3.setStatus(Status.BEING_ADDRESSED);
    ticketOutWOComment3.setTicketId(101);
    
    assertNotEquals(ticketOutWOComment.hashCode(), ticketOutWOComment2.hashCode());
    assertEquals(ticketOutWOComment.hashCode(), ticketOutWOComment3.hashCode());
  }
  
  @Test
  void test_ticketWOComment_equals() {
    TicketOutWOComment ticketOutWOComment = new TicketOutWOComment();
    ticketOutWOComment.setDepartment("HR");
    ticketOutWOComment.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment.setStatus(Status.BEING_ADDRESSED);
    ticketOutWOComment.setTicketId(101);
    
    TicketOutWOComment ticketOutWOComment2 = new TicketOutWOComment();
    ticketOutWOComment2.setDepartment("HR");
    ticketOutWOComment2.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment2.setStatus(Status.OPEN);
    ticketOutWOComment2.setTicketId(101);
    
    TicketOutWOComment ticketOutWOComment3 = new TicketOutWOComment();
    ticketOutWOComment3.setDepartment("HR");
    ticketOutWOComment3.setEmployee("ayushi@nucleusteq,com");
    ticketOutWOComment3.setStatus(Status.BEING_ADDRESSED);
    ticketOutWOComment3.setTicketId(101);
    
    assertThat(!ticketOutWOComment.equals(ticketOutWOComment2));
    assertThat(ticketOutWOComment.equals(ticketOutWOComment3));
  }
}
