import { ApiProperty } from '@nestjs/swagger';

export class ScheduleDto {
  @ApiProperty()
  hardwareKey: string;
}
