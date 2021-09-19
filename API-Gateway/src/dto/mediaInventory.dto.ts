import { ApiProperty } from '@nestjs/swagger';

export class MediaInventoryDto {
  @ApiProperty()
  serverKey: string;
  @ApiProperty()
  hardwareKey: string;
  @ApiProperty()
  mediaInventory: string;
}
