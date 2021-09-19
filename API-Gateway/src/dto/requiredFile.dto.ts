import { ApiProperty } from '@nestjs/swagger';

export class RequiredFileDto {
  @ApiProperty()
  hardwareKey: string;
}
